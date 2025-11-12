package ru.MarkMoskvitin.NauJava.repo;

import org.springframework.data.repository.CrudRepository;
import ru.MarkMoskvitin.NauJava.entity.Report;

public interface ReportRepository extends CrudRepository<Report,Long> {
}
