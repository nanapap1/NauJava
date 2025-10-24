package ru.MarkMoskvitin.NauJava.service;

public interface Services<T>
{
    void updateDescription(Long id, String descr);
    void createTask(Long id, String description);
    T findById(Long id);
    void deleteById(Long id);
}
