package ru.MarkMoskvitin.NauJava;
import java.util.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.MarkMoskvitin.NauJava.entity.Group;
import ru.MarkMoskvitin.NauJava.entity.Task;
import ru.MarkMoskvitin.NauJava.entity.User;
import ru.MarkMoskvitin.NauJava.models.Role;
import ru.MarkMoskvitin.NauJava.repo.GroupRepository;
import ru.MarkMoskvitin.NauJava.repo.TaskRepository;
import ru.MarkMoskvitin.NauJava.repo.UserRepository;
import ru.MarkMoskvitin.NauJava.service.UserService;
import ru.MarkMoskvitin.NauJava.service.UserServiceImp;
import ru.MarkMoskvitin.NauJava.specrepo.TaskRepoCustomImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.springframework.dao.DataAccessException;

public class UserTest {
    private UserService userService;
    private UserRepository userRepository;
    private TaskRepository taskRepository;
    private PlatformTransactionManager transactionManager;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        this.userRepository = Mockito.mock(UserRepository.class);
        this.taskRepository = Mockito.mock(TaskRepository.class);
        this.transactionManager = Mockito.mock(PlatformTransactionManager.class);
        this.passwordEncoder = Mockito.mock(PasswordEncoder.class);
        this.userService = new UserServiceImp(taskRepository,userRepository,transactionManager,passwordEncoder);
    }

    @Test
    public void addUserTest(){
        User user = new User();
        user.setUsername(UUID.randomUUID().toString());
        String pass = UUID.randomUUID().toString();
        user.setPassword(pass);
        Set<Role> roles = new java.util.HashSet<>();
        roles.add(Role.USER);
        when(this.passwordEncoder.encode(pass)).thenReturn(pass);
        when(this.userRepository.save(user)).thenReturn(user);
        userService.addUser(user);
        Mockito.verify(passwordEncoder).encode(pass);
        Mockito.verify(userRepository).save(user);
        assertEquals(user.getPassword(),pass);
        assertEquals(user.getRoles(),roles);
    }

    @Test
    void testDeleteUserInTx() {

        TransactionStatus transactionStatus = Mockito.mock(TransactionStatus.class);

        User user = new User();
        when(this.userRepository.save(user)).thenReturn(user);
        String user1 = UUID.randomUUID().toString();
        user.setUsername(user1);
        user.setPassword(UUID.randomUUID().toString());

        Task task1 = new Task();
        task1.setUser(user);
        String title1 = UUID.randomUUID().toString();
        task1.setTitle(title1);
        when(this.taskRepository.save(task1)).thenReturn(task1);

        Task task2 = new Task();
        task2.setUser(user);
        String title2 = UUID.randomUUID().toString();
        task2.setTitle(title2);
        when(this.taskRepository.save(task2)).thenReturn(task2);

        List<Task> tasks = List.of(task1,task2);

        when(transactionManager.getTransaction(Mockito.any(DefaultTransactionDefinition.class)))
                .thenReturn(transactionStatus);

        when(taskRepository.findByUser(user1)).thenReturn(tasks);
        when(userRepository.findByUsername(user1)).thenReturn(user);
        userService.deleteUser(user.getUsername());
        Mockito.verify(taskRepository).findByUser(user1);
        Mockito.verify(taskRepository).delete(task1);
        Mockito.verify(taskRepository).delete(task2);
        Mockito.verify(userRepository).delete(user);
        Mockito.verify(transactionManager).commit(transactionStatus);
    }

    @Test
    void testNegativeDeleteUserInTx() throws DataAccessException{

        TransactionStatus transactionStatus = Mockito.mock(TransactionStatus.class);

        User user = new User();
        when(this.userRepository.save(user)).thenReturn(user);
        String user1 = UUID.randomUUID().toString();
        user.setUsername(user1);
        user.setPassword(UUID.randomUUID().toString());

        Task task1 = new Task();
        task1.setUser(user);
        String title1 = UUID.randomUUID().toString();
        task1.setTitle(title1);
        when(this.taskRepository.save(task1)).thenReturn(task1);

        Task task2 = new Task();
        task2.setUser(user);
        String title2 = UUID.randomUUID().toString();
        task2.setTitle(title2);
        when(this.taskRepository.save(task2)).thenReturn(task2);

        List<Task> tasks = List.of(task1,task2);

        when(transactionManager.getTransaction(Mockito.any(DefaultTransactionDefinition.class)))
                .thenReturn(transactionStatus);

        when(taskRepository.findByUser(user1)).thenThrow(DataRetrievalFailureException.class);
        when(userRepository.findByUsername(user1)).thenReturn(user);
        assertThrows(DataAccessException.class, () -> userService.deleteUser(user.getUsername()));
        Mockito.verify(transactionManager).rollback(transactionStatus);
    }



}