package ru.MarkMoskvitin.NauJava;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import ru.MarkMoskvitin.NauJava.entity.Task;
import ru.MarkMoskvitin.NauJava.entity.User;
import ru.MarkMoskvitin.NauJava.repo.TaskRepository;
import ru.MarkMoskvitin.NauJava.repo.UserRepository;
import ru.MarkMoskvitin.NauJava.service.UserServiceImp;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TransactionTest {
    private final UserServiceImp userService;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TransactionTest(UserServiceImp userService, TaskRepository taskRepository, UserRepository userRepository) {
        this.userService = userService;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Test
    void testDeleteUserInTx() {
        User user = new User();
        user.setUsername(UUID.randomUUID().toString());
        userRepository.save(user);

        Task task1 = new Task();
        task1.setTitle(UUID.randomUUID().toString());
        task1.setUser(user);
        taskRepository.save(task1);

        Task task2 = new Task();
        task2.setTitle(UUID.randomUUID().toString());
        task2.setUser(user);
        taskRepository.save(task2);

        userService.deleteUser(user.getUsername());
        Optional<User> foundRole = userRepository.findById(user.getId());
        Assertions.assertTrue(foundRole.isEmpty());

        Optional<Task> foundTask1 = taskRepository.findById(task1.getId());
        Assertions.assertTrue(foundTask1.isEmpty());

        Optional<Task> foundTask2 = taskRepository.findById(task2.getId());
        Assertions.assertTrue(foundTask2.isEmpty());

    }

    @Test
    void testDeleteRoleInTx_not() {
        User user = new User();
        user.setUsername(UUID.randomUUID().toString());
        userRepository.save(user);


        Task task1 = new Task();
        task1.setTitle(UUID.randomUUID().toString());
        task1.setUser(user);
        taskRepository.save(task1);

        user.setUsername("s");

        Task task2 = new Task();
        task2.setTitle(UUID.randomUUID().toString());
        task2.setUser(user);
        taskRepository.save(task2);

        assertThrows(DataAccessException.class, () -> userService.deleteUser(user.getUsername()));
        Optional<User> foundRole = userRepository.findById(user.getId());
        Assertions.assertFalse(foundRole.isEmpty());

        Optional<Task> foundTask1 = taskRepository.findById(task1.getId());
        Assertions.assertFalse(foundTask1.isEmpty());

        Optional<Task> foundTask2 = taskRepository.findById(task2.getId());
        Assertions.assertFalse(foundTask2.isEmpty());

    }
}
