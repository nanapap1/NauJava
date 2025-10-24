package ru.MarkMoskvitin.NauJava.repo;
import org.springframework.data.repository.CrudRepository;
import ru.MarkMoskvitin.NauJava.entity.PushTemplate;
public interface PushRepository extends CrudRepository<PushTemplate,Long> {
}
