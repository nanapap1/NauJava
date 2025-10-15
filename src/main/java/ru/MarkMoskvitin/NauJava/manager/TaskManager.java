package ru.MarkMoskvitin.NauJava.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.MarkMoskvitin.NauJava.task.*;

@Component
public class TaskManager implements TaskCRUD<Task> {
    private final TaskList tasks;

    @Autowired
    public TaskManager(TaskList tasks)
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
            if (t.getId().equals(id)) {
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
