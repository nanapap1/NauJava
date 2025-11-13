package ru.MarkMoskvitin.NauJava.entity;

import jakarta.persistence.*;
import ru.MarkMoskvitin.NauJava.models.StatusReport;


@Entity
@Table(name="reports")
public class Report {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column
    private StatusReport status;
    @Column
    private String content_tasks;
    @Column
    private String content;
    @Column
    private Long thread1Time;
    @Column
    private Long thread2Time;
    @Column
    private Long executionTime;

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public Long getThread1Time() {
        return thread1Time;
    }

    public void setThread1Time(Long thread1Time) {
        this.thread1Time = thread1Time;
    }

    public Long getThread2Time() {
        return thread2Time;
    }

    public void setThread2Time(Long thread2Time) {
        this.thread2Time = thread2Time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public StatusReport getStatus() {
        return status;
    }

    public void setStatus(StatusReport status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent_tasks() {
        return content_tasks;
    }

    public void setContent_tasks(String content_tasks) {
        this.content_tasks = content_tasks;
    }
}
