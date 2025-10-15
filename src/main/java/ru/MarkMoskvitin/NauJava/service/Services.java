package ru.MarkMoskvitin.NauJava.service;

import ru.MarkMoskvitin.NauJava.task.Task;

import java.util.Date;

public interface Services<T>
{
    void updateDescription(Long id, String descr);
    void createTask(Long id, String description, String status, Date end,boolean hasPush);
    T findById(Long id);
    void deleteById(Long id);
}
