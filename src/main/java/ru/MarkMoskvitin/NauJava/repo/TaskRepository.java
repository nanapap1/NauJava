package ru.MarkMoskvitin.NauJava.repo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.MarkMoskvitin.NauJava.entity.Task;
import ru.MarkMoskvitin.NauJava.entity.User;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task,Long> {
    List<Task> findByTitleOrFinish(String title,String Finish);


    @Query("FROM Task WHERE group.name = :groupTitle")
    List<Task> findByGroup(String groupTitle);

    @Query("FROM Task WHERE user.username = :username")
    List<Task> findByUser(String username);
}
