package encryptor;

import encryptor.managers.ApplicationManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class MainApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MainApp.class, args);
        ApplicationManager applicationManager = context.getBean(ApplicationManager.class);
        applicationManager.run();

        // C:\\Ben\\Encryptor_messages\\encrypted
        // C:\\Ben\\Encryptor_messages\\encrypted\\key.txt
    }
}