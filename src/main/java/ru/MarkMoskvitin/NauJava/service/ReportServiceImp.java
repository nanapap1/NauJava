package ru.MarkMoskvitin.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.MarkMoskvitin.NauJava.entity.Group;
import ru.MarkMoskvitin.NauJava.entity.Report;
import ru.MarkMoskvitin.NauJava.entity.Subtask;
import ru.MarkMoskvitin.NauJava.entity.Task;
import ru.MarkMoskvitin.NauJava.models.StatusReport;
import ru.MarkMoskvitin.NauJava.repo.*;

import java.util.concurrent.CompletableFuture;

@Service
public class ReportServiceImp {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final ReportRepository reportRepository;
    private final SubtaskRepository subtaskRepository;

    @Autowired
    public ReportServiceImp(UserRepository userRepository, TaskRepository taskRepository, ReportRepository reportRepository, GroupRepository groupRepository, SubtaskRepository subtaskRepository)
    {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.reportRepository = reportRepository;
        this.subtaskRepository = subtaskRepository;
    }

    public Long addReport(Report report) {
        report.setStatus(StatusReport.CREATE);
        Report savedReport = reportRepository.save(report);
        return savedReport.getId();
    }

    public String getContent(Long id) {
        Report report = reportRepository.findById(id).orElseThrow();
        return "User amount:" + report.getContent() + "/n" + "Tasks: " + report.getContent_tasks();
    }

    public Report getReport(Long id) {
           return reportRepository.findById(id).orElseThrow();
    }

    public void createReport(Long id){
        Report report = reportRepository.findById(id).orElseThrow();
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {

            Thread thread1 = new Thread(() -> {
                report.setContent(Long.toString(userRepository.count()));
            });

            Thread thread2 = new Thread(() -> {
                Iterable<Subtask> subtaskRepository = this.subtaskRepository.findAll();
                String result = "";
                for (Subtask subtask : subtaskRepository){
                    result += "Title: " + subtask.getTitle();
                    if (subtask.getBody() != null){
                        result += " | Description: " +subtask.getBody() + "\n";
                    }
                    else {
                        result += "\n";
                    }
                }
                report.setContent_tasks(result);
            });
            long startTime = System.currentTimeMillis();
            long startTime1 = System.currentTimeMillis();
            thread1.start();
            long startTime2 = System.currentTimeMillis();
            thread2.start();

            try {
                thread1.join();
                report.setThread1Time(System.currentTimeMillis() - startTime1);
                thread2.join();
                report.setThread2Time(System.currentTimeMillis() - startTime2);
                report.setExecutionTime(System.currentTimeMillis() - startTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                report.setStatus(StatusReport.ERROR);
                reportRepository.save(report);
                return "end";
            }
            report.setStatus(StatusReport.FINISHED);
            reportRepository.save(report);
            return "end";
        });
    }
}
