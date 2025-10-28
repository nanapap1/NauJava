package ru.MarkMoskvitin.NauJava.repo;
import org.springframework.data.repository.CrudRepository;
import ru.MarkMoskvitin.NauJava.entity.Subtask;

public interface SubtaskRepository extends CrudRepository<Subtask,Long> {
}
