package ru.MarkMoskvitin.NauJava.service;

import ru.MarkMoskvitin.NauJava.entity.User;

public interface UserService {
    void deleteUser(String username);
    void addUser(User user);
}
