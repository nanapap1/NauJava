package ru.MarkMoskvitin.NauJava.repo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.MarkMoskvitin.NauJava.entity.PushTemplate;


@RepositoryRestResource(path = "pushtemplates")
public interface PushTemplateRepository extends CrudRepository<PushTemplate,Long> {
}
