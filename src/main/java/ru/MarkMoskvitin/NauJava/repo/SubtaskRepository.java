package ru.MarkMoskvitin.NauJava.repo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.MarkMoskvitin.NauJava.entity.Subtask;

@RepositoryRestResource(path = "subtasks")
public interface SubtaskRepository extends CrudRepository<Subtask,Long> {
}
