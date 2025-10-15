package ru.MarkMoskvitin.NauJava.task;

import java.util.Date;

public class Task
{
    private Long id;
    private String description;
    private String status;
    private Date finish;
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
    public void setDescription(String login)
    {
        this.description = login;
    }
    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
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
