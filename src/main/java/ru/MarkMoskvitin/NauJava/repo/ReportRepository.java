package ru.MarkMoskvitin.NauJava.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.MarkMoskvitin.NauJava.entity.Report;
@RepositoryRestResource(path = "report")
public interface ReportRepository extends CrudRepository<Report,Long> {
}
