package ru.MarkMoskvitin.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.MarkMoskvitin.NauJava.entity.User;
import ru.MarkMoskvitin.NauJava.models.Role;
import ru.MarkMoskvitin.NauJava.repo.TaskRepository;
import ru.MarkMoskvitin.NauJava.repo.UserRepository;
import ru.MarkMoskvitin.NauJava.entity.Task;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService, UserDetailsService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final PlatformTransactionManager transactionManager;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImp(TaskRepository taskRepository, UserRepository userRepository,
                           PlatformTransactionManager transactionManager, PasswordEncoder passwordEncoder)
    {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.transactionManager = transactionManager;
        this.passwordEncoder = passwordEncoder;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),mapRoles(user.getRoles()));
    }

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new java.util.HashSet<>();
        roles.add(Role.USER);
        user.setRoles(roles);
        userRepository.save(user);
    }

    private Collection<? extends GrantedAuthority> mapRoles(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).collect(Collectors.toSet());
    }
}