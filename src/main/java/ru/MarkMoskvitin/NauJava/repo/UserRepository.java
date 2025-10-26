package ru.MarkMoskvitin.NauJava.repo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.MarkMoskvitin.NauJava.entity.User;

@RepositoryRestResource(path = "users")
public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
