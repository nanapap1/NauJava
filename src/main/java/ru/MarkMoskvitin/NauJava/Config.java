package ru.MarkMoskvitin.NauJava;


import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.MarkMoskvitin.NauJava.task.*;

@Configuration
public class Config
{
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public TaskList tasksContainer()
    {
        return new TaskList();
    }
}