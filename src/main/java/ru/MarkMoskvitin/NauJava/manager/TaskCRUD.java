package ru.MarkMoskvitin.NauJava.manager;

public interface TaskCRUD<T> {
        void create(T task);
        T read(Long id);
        void updateStatus(Long task,String status);
        void delete(Long id);
}
