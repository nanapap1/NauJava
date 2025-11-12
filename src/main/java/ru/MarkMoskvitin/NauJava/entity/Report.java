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
    private String content;

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
}
