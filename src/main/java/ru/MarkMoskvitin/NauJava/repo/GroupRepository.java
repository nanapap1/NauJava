package ru.MarkMoskvitin.NauJava.repo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.MarkMoskvitin.NauJava.entity.Group;

@RepositoryRestResource(path = "groups")
public interface GroupRepository extends CrudRepository<Group,Long> {
}
