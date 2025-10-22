package ru.MarkMoskvitin.NauJava.specrepo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import ru.MarkMoskvitin.NauJava.entity.Group;
import ru.MarkMoskvitin.NauJava.entity.Task;
import ru.MarkMoskvitin.NauJava.entity.User;

@Repository
public class TaskRepoCustomImpl implements TaskRepositoryCustom{


    private final EntityManager entityManager;
    @Autowired
    public TaskRepoCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Task> findByTitleOrEnd(String title, String end)
        {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
            Root<Task> taskRoot = criteriaQuery.from(Task.class);
            Predicate titlePredicate = criteriaBuilder.equal(taskRoot.get("title"), title);
            Predicate endPredicate = criteriaBuilder.equal(taskRoot.get("end"), end);
            Predicate allPredicate = criteriaBuilder.or(titlePredicate,endPredicate);
            criteriaQuery.select(taskRoot).where(allPredicate);
            return entityManager.createQuery(criteriaQuery).getResultList();
        }

    @Override
    public List<Task> findByGroup(String groupTitle)
        {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
            Root<Task> taskRoot = criteriaQuery.from(Task.class);
            Join<Task, Group> group = taskRoot.join("group", JoinType.INNER);
            Predicate titlePredicate = criteriaBuilder.equal(group.get("title"), groupTitle);
            criteriaQuery.select(taskRoot).where(titlePredicate);
            return entityManager.createQuery(criteriaQuery).getResultList();
        }

    @Override
    public List<Task> findByUser(String username)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
        Root<Task> taskRoot = criteriaQuery.from(Task.class);
        Join<Task, User> user = taskRoot.join("user", JoinType.INNER);
        Predicate titlePredicate = criteriaBuilder.equal(user.get("id"), username);
        criteriaQuery.select(taskRoot).where(titlePredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}

