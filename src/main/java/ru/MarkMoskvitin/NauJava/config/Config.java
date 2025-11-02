package ru.MarkMoskvitin.NauJava.config;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import ru.MarkMoskvitin.NauJava.commands.Commands;

@Configuration
public class Config
{
    @Value("${app.name}")
    private String name;
    @Value("${app.version}")
    private String version;

    @PostConstruct
    private void postConstruct() {
        System.out.println("Название приложения: " + this.name);
        System.out.println("Версия приложения: " + this.version);

    }

    @Autowired
    private Commands commandProcessor;
    /*@Bean
    public CommandLineRunner commandScanner()
    {
        return args ->
        {
            try (Scanner scanner = new Scanner(System.in))
            {
                System.out.println("Введите команду 'exit' для выхода");
                while (true) {
                    System.out.print("> ");
                    String input = scanner.nextLine();

                    if ("exit".equalsIgnoreCase(input.trim()))
                    {
                        System.out.println("Выход из программы...");
                        break;
                    }
                    commandProcessor.processCommands(input);
                }
            }
        };
    }*/
}