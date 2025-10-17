package ru.MarkMoskvitin.NauJava.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.MarkMoskvitin.NauJava.service.TaskService;
import ru.MarkMoskvitin.NauJava.entity.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class Commands {
        private final TaskService taskService;
        @Autowired
        public Commands(TaskService taskService)
        {
            this.taskService = taskService;
        }


         public void processCommands(String input)
         {
            String[] cmd = input.split(" ");
            switch (cmd[0])
            {
                case "create" ->
                {
                    if (cmd.length==5) {
                        final String pattern = "dd/MM/yyyy";
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                        LocalDate date= LocalDate.parse(cmd[3], formatter);
                        taskService.createTask(Long.valueOf(cmd[1]), cmd[2], "not done", date, cmd[4].equalsIgnoreCase("yes"));
                        System.out.println("Задача успешно добавлена.");
                    }
                    else
                        System.out.println("Ошибка в команде");
                }
                case "delete" ->
                {
                    if (cmd.length ==2)
                        taskService.deleteById(Long.valueOf(cmd[1]));
                    else
                        System.out.println("Ошибка в команде");
                }
                case "find" -> {
                    if (cmd.length == 2) {
                        Task t = taskService.findById(Long.valueOf(cmd[1]));
                        if (t == null) {
                            System.out.println("Задача не найдена");
                        }
                        else  {
                            final String pattern = "dd/MM/yyyy";
                            DateTimeFormatter df =  DateTimeFormatter.ofPattern(pattern);
                            System.out.printf("Цель: %s, успеть до: %s, уведомления: %s\n", t.getDescription(), df.format(t.getFinish()), t.isHasPush() ? "Да" : "Нет");
                        }
                       }
                    else
                        System.out.println("Ошибка в команде");

                }
                case "descr" -> {
                    if (cmd.length == 3) {
                        taskService.updateDescription(Long.valueOf(cmd[1]),cmd[2]);
                    }
                    else
                        System.out.println("Ошибка в команде");
                }
                default -> System.out.println("Введена неизвестная команда... ");
            }

        }
    }

