package ru.MarkMoskvitin.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.MarkMoskvitin.NauJava.entity.User;
import ru.MarkMoskvitin.NauJava.repo.TaskRepository;
import ru.MarkMoskvitin.NauJava.repo.UserRepository;
import ru.MarkMoskvitin.NauJava.entity.Task;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final PlatformTransactionManager transactionManager;
    @Autowired
    public UserServiceImp(TaskRepository taskRepository, UserRepository userRepository,
                           PlatformTransactionManager transactionManager)
    {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.transactionManager = transactionManager;
    }
    @Override
    public void deleteUser(String username)
    {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            List<Task> tasks = taskRepository.findByUser(username);
            for (Task task : tasks){
                taskRepository.delete(task);
            }

            User user = userRepository.findByUsername(username);
            userRepository.delete(user);
            transactionManager.commit(status);
        }
        catch (DataAccessException ex)
        {
            transactionManager.rollback(status);
            throw ex;
        }
    }
}