package ru.MarkMoskvitin.NauJava.repo;
import org.springframework.data.repository.CrudRepository;
import ru.MarkMoskvitin.NauJava.entity.Group;
public interface GroupRepository extends CrudRepository<Group,Long> {
}
