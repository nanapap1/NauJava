package ru.MarkMoskvitin.NauJava.entity;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
@Component
public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }
}
