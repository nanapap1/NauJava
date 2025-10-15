package ru.MarkMoskvitin.NauJava.task;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

public class Task
{
    private Long id;
    private String description;
    private String status;
    private LocalDate finish;
    private boolean hasPush;

    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String decscr)
    {
        this.description = decscr;
    }
    public LocalDate getFinish() {
        return finish;
    }

    public void setFinish(LocalDate finish) {
        this.finish = finish;
    }

    public boolean isHasPush() {
        return hasPush;
    }

    public void setHasPush(boolean hasPush) {
        this.hasPush = hasPush;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
