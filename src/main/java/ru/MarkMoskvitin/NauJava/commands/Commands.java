package ru.MarkMoskvitin.NauJava.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.MarkMoskvitin.NauJava.repo.TaskRepository;
import ru.MarkMoskvitin.NauJava.entity.Task;
import java.util.List;

@Component
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class Commands {
        private final TaskRepository taskRepository;
        @Autowired
        public Commands(TaskRepository taskRepository)
        {
            this.taskRepository = taskRepository;
        }

         public void processCommands(String input)
         {
            String[] cmd = input.split(" ");
            switch (cmd[0])
            {
                case "create" ->
                {
                    if (cmd.length==5) {
                        Task task = new Task();
                        task.setTitle(cmd[2]);
                        task.setBody(cmd[3]);
                        task.setEnd(cmd[3]);
                        taskRepository.save(task);
                        System.out.println("Задача успешно добавлена.");
                    }
                    else
                        System.out.println("Ошибка в команде");
                }
                case "delete" ->
                {
                    if (cmd.length ==2)
                        taskRepository.deleteById(Long.valueOf(cmd[1]));
                    else
                        System.out.println("Ошибка в команде");
                }
                case "find" -> {
                    if (cmd.length == 2) {
                        List<Task> t = taskRepository.findByUser(cmd[1]);
                        if (t == null) {
                            System.out.println("Задача не найдена");
                        }
                        else  {
                            for(Task one: t)
                                System.out.printf("Названия: %s, успеть до: %s",one.getTitle(),one.getEnd());
                        }
                       }
                    else
                        System.out.println("Ошибка в команде");

                }
                case "group" -> {
                    if (cmd.length == 3) {
                        List<Task> t= taskRepository.findByGroup(cmd[1]);
                        for(Task one: t)
                            System.out.printf("Названия: %s, успеть до: %s",one.getTitle(),one.getEnd());
                    }
                    else
                        System.out.println("Ошибка в команде");
                }
                default -> System.out.println("Введена неизвестная команда... ");
            }
        }
    }

