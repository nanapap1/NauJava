package ru.MarkMoskvitin.NauJava;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.MarkMoskvitin.NauJava.entity.Group;
import ru.MarkMoskvitin.NauJava.entity.Task;
import ru.MarkMoskvitin.NauJava.entity.User;
import ru.MarkMoskvitin.NauJava.repo.GroupRepository;
import ru.MarkMoskvitin.NauJava.repo.TaskRepository;
import ru.MarkMoskvitin.NauJava.repo.UserRepository;
import ru.MarkMoskvitin.NauJava.specrepo.TaskRepoCustomImpl;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class TaskTest {
    private final TaskRepository taskRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final TaskRepoCustomImpl taskCriticalAPI;
    @Autowired
    TaskTest(TaskRepository taskRepository,GroupRepository groupRepository,TaskRepoCustomImpl taskCriticalAPI,UserRepository userRepository)
    {
        this.taskRepository = taskRepository;
        this.groupRepository = groupRepository;
        this.taskCriticalAPI = taskCriticalAPI;
        this.userRepository = userRepository;
    }

    @Test
    void testFindTaskByTitleAndEnd()
    {
        String taskTitle = UUID.randomUUID().toString();
        String end = UUID.randomUUID().toString();
        Task task = new Task();
        task.setTitle(taskTitle);
        task.setEnd(end);
        taskRepository.save(task);
        String endWrong = "25/10/2025";
        String titleWrong = "Вынести мусор";

        Task foundTask1 = taskRepository.findByTitleOrFinish(task.getTitle(),task.getEnd()).getFirst();
        Assertions.assertNotNull(foundTask1);
        Assertions.assertEquals(task.getId(), foundTask1.getId());
        Assertions.assertEquals(task.getTitle(), foundTask1.getTitle());
        Assertions.assertEquals(task.getEnd(), foundTask1.getEnd());

        Task foundTask2 = taskRepository.findByTitleOrFinish(task.getTitle(),endWrong).getFirst();
        Assertions.assertNotNull(foundTask2);
        Assertions.assertEquals(task.getId(), foundTask2.getId());
        Assertions.assertEquals(task.getTitle(), foundTask2.getTitle());

        Task foundTask3 = taskRepository.findByTitleOrFinish(titleWrong,task.getEnd()).getFirst();
        Assertions.assertNotNull(foundTask3);
        Assertions.assertEquals(task.getId(), foundTask3.getId());
        Assertions.assertEquals(task.getEnd(), foundTask3.getEnd());

        assertThrows(NoSuchElementException.class, () -> taskRepository.findByTitleOrFinish(titleWrong,endWrong).getFirst());
    }


    @Test
    void testFindTaskByTitleAndEnd_Critical()
    {
        String taskTitle = UUID.randomUUID().toString();
        String end = UUID.randomUUID().toString();
        Task task = new Task();
        task.setTitle(taskTitle);
        task.setEnd(end);
        taskRepository.save(task);
        String endWrong = "25/10/2025";
        String titleWrong = "Вынести мусор";

        Task foundTask1 = taskCriticalAPI.findByTitleOrFinish(task.getTitle(),task.getEnd()).getFirst();
        Assertions.assertNotNull(foundTask1);
        Assertions.assertEquals(task.getId(), foundTask1.getId());
        Assertions.assertEquals(task.getTitle(), foundTask1.getTitle());
        Assertions.assertEquals(task.getEnd(), foundTask1.getEnd());

        Task foundTask2 = taskCriticalAPI.findByTitleOrFinish(task.getTitle(),endWrong).getFirst();
        Assertions.assertNotNull(foundTask2);
        Assertions.assertEquals(task.getId(), foundTask2.getId());
        Assertions.assertEquals(task.getTitle(), foundTask2.getTitle());

        Task foundTask3 = taskCriticalAPI.findByTitleOrFinish(titleWrong,task.getEnd()).getFirst();
        Assertions.assertNotNull(foundTask3);
        Assertions.assertEquals(task.getId(), foundTask3.getId());
        Assertions.assertEquals(task.getEnd(), foundTask3.getEnd());

        assertThrows(NoSuchElementException.class, () -> taskCriticalAPI.findByTitleOrFinish(titleWrong,endWrong).getFirst());
    }

    @Test
    void testFindTaskByGroup()
    {
        Task task = new Task();
        Group group = new Group();
        group.setName(UUID.randomUUID().toString());
        task.setGroup(group);
        groupRepository.save(group);
        taskRepository.save(task);

        Task foundTask1 = taskRepository.findByGroup(group.getName()).getFirst();
        Assertions.assertNotNull(foundTask1);
        Assertions.assertEquals(task.getId(), foundTask1.getId());
    }

    @Test
    void testFindTaskByGroup_Critical()
    {
        Task task = new Task();
        Group group = new Group();
        group.setName(UUID.randomUUID().toString());
        task.setGroup(group);
        groupRepository.save(group);
        taskRepository.save(task);
        Task foundTask1 = taskCriticalAPI.findByGroup(group.getName()).getFirst();
        Assertions.assertNotNull(foundTask1);
        Assertions.assertEquals(task.getId(), foundTask1.getId());
    }

    @Test
    void testFindTaskByUser()
    {
        Task task = new Task();
        User user = new User();
        user.setUsername(UUID.randomUUID().toString());
        task.setUser(user);
        userRepository.save(user);
        taskRepository.save(task);

        Task foundTask1 = taskRepository.findByUser(user.getUsername()).getFirst();
        Assertions.assertNotNull(foundTask1);
        Assertions.assertEquals(task.getId(), foundTask1.getId());
    }

    @Test
    void testFindTaskByUser_Critical()
    {
        Task task = new Task();
        User user = new User();
        user.setUsername(UUID.randomUUID().toString());
        task.setUser(user);
        userRepository.save(user);
        taskRepository.save(task);

        Task foundTask1 = taskCriticalAPI.findByUser(user.getUsername()).getFirst();
        Assertions.assertNotNull(foundTask1);
        Assertions.assertEquals(task.getId(), foundTask1.getId());
    }



}
