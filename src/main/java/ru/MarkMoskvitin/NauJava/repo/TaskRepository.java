package ru.MarkMoskvitin.NauJava.repo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.MarkMoskvitin.NauJava.entity.Task;
import ru.MarkMoskvitin.NauJava.entity.User;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task,Long> {
    List<Task> findByTitleOrEnd(String title,String end);


    @Query("SELECT t FROM Task t WHERE t.group = :groupTitle")
    List<Task> findByGroup(String groupTitle);

    @Query("SELECT t FROM Task t WHERE t.user = :username")
    List<Task> findByUser(String username);
}
