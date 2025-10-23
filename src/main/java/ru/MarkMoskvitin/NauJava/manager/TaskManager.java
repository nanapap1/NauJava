package ru.MarkMoskvitin.NauJava.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.MarkMoskvitin.NauJava.entity.Task;

import java.util.List;
import java.util.Objects;

@Component
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class TaskManager implements TaskCRUD<Task> {
    private final List<Task> tasks;

    @Autowired
    public TaskManager(List<Task> tasks)
    {
        this.tasks = tasks;
    }

    @Override
    public void create(Task task) {
        this.tasks.add(task);
    }

    @Override
    public Task read(Long id)
    {
        for(Task t : this.tasks) {
            if (Objects.equals(t.getId(), id)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public void updateStatus(Long id,String status) {
       Task task = this.read(id);
       if (task!=null)
          task.setStatus(status);
    }

    @Override
    public void delete(Long id)
    {
        Task task = this.read(id);
        if (task!=null)
               this.tasks.remove(task);
    }


}
