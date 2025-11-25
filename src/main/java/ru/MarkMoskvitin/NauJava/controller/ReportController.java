package ru.MarkMoskvitin.NauJava.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.MarkMoskvitin.NauJava.entity.Report;
import ru.MarkMoskvitin.NauJava.service.ReportServiceImp;

@Controller
public class ReportController {
    @Autowired
    private ReportServiceImp reportService;

    @GetMapping("/report/create")
    public String createReport(){
        Report report = new Report();
        Long id = reportService.addReport(report);
        reportService.createReport(id);
        return "reportCreated";
    }

    @GetMapping("/report/find/{id}")
    public ModelAndView getReport(@PathVariable Long id) throws Exception {
        Report report = reportService.getReport(id);
        ModelAndView modelAndView;

        switch (report.getStatus()) {
            case CREATE:
                modelAndView = new ModelAndView("reportNotReady");
                break;
            case ERROR:
                modelAndView = new ModelAndView("reportError");
                break;
            default:
                modelAndView = new ModelAndView("reportReady");
                modelAndView.addObject("report", report);
                modelAndView.addObject("subtask", report.getContent_tasks().split("\n"));
                break;
        }
        return modelAndView;
    }

}
