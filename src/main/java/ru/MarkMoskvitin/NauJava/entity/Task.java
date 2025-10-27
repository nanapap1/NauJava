package ru.MarkMoskvitin.NauJava.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tasks")
public class Task
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String body;
    @Column
    private String link;
    @Column
    private String finish;
    @ManyToOne
    private User user;
    @ManyToOne
    private PushTemplate pushTemplate;
    @ManyToOne
    private Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }


    public PushTemplate getPushTemplate() {
        return pushTemplate;
    }

    public void setPushTemplate(PushTemplate pushTemplate) {
        this.pushTemplate = pushTemplate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEnd() {
        return finish;
    }

    public void setEnd(String end) {
        this.finish = end;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
