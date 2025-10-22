package ru.MarkMoskvitin.NauJava.repo;
import org.springframework.data.repository.CrudRepository;
import ru.MarkMoskvitin.NauJava.entity.User;
public interface UserRepository extends CrudRepository<User,Long> {
}
