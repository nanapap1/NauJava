package ru.MarkMoskvitin.NauJava.service;


import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.MarkMoskvitin.NauJava.entity.*;
import ru.MarkMoskvitin.NauJava.repo.TaskRepository;

@Service
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class TaskService implements Services<Task> {
        private final TaskRepository taskRepository;
        @Autowired
        public TaskService(TaskRepository taskRepository)
        {
            this.taskRepository = taskRepository;
        }
        @Override
        public void createTask(Long id, String description)
        {
            Task t = new Task();
            System.out.println(id);
            t.setId(id);
            t.setBody(description);
            taskRepository.save(t);

        }
        @Override
        public Task findById(Long id)
        {
            return taskRepository.existsById(id) ? taskRepository.findById(id).get() : null;
        }
        @Override
        public void deleteById(Long id)
        {
            Task t = taskRepository.findById(id).get();
            if (t == null) {
                System.out.println("Задача не найдена");
            }
            else
                taskRepository.deleteById(id);
        }
        @Override
        public void updateDescription(Long id, String descr)
        {
            Task t = taskRepository.findById(id).get();
            if (t == null) {
                System.out.println("Задача не найдена");
            }
            else
                t.setBody(descr);
        }


}
