package ru.MarkMoskvitin.NauJava.manager;

public interface TaskCRUD<T> {
        void create(T task);
        T read(Long id);
        void delete(Long id);
}
