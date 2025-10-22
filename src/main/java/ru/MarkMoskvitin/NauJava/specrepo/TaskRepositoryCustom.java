package ru.MarkMoskvitin.NauJava.specrepo;

import ru.MarkMoskvitin.NauJava.entity.Task;

import java.util.List;

public interface TaskRepositoryCustom {

    List<Task> findByTitleOrEnd(String title, String end);
    List<Task> findByGroup(String groupTitle);
}
