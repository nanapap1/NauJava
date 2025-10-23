package ru.MarkMoskvitin.NauJava.service;


import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.MarkMoskvitin.NauJava.entity.*;
import ru.MarkMoskvitin.NauJava.manager.*;

import java.time.LocalDate;

@Service
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class TaskService implements Services<Task> {
        private final TaskManager taskManager;
        @Autowired
        public TaskService(TaskManager taskManager)
        {
            this.taskManager = taskManager;
        }
        @Override
        public void createTask(Long id, String description, String status, LocalDate end, boolean hasPush)
        {
            Task t = new Task();
            System.out.println(id);
            t.setId(id);
            t.setDescription(description);
            t.setStatus(status);
            t.setFinish(end);
            t.setHasPush(hasPush);
            taskManager.create(t);

        }
        @Override
        public Task findById(Long id)
        {
            return taskManager.read(id);
        }
        @Override
        public void deleteById(Long id)
        {
            Task t = taskManager.read(id);
            if (t == null) {
                System.out.println("Задача не найдена");
            }
            else
               taskManager.delete(id);
        }
        @Override
        public void updateDescription(Long id, String descr)
        {
            Task t = taskManager.read(id);
            if (t == null) {
                System.out.println("Задача не найдена");
            }
            else
                t.setDescription(descr);
        }


}
