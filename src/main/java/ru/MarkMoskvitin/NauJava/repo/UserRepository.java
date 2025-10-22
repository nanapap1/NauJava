package ru.MarkMoskvitin.NauJava.repo;
import org.springframework.data.repository.CrudRepository;
import ru.MarkMoskvitin.NauJava.entity.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
