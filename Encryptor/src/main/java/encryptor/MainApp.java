package encryptor;

import encryptor.managers.EncryptionSystemManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MainApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MainApp.class, args);
        EncryptionSystemManager encryptionManager = context.getBean(EncryptionSystemManager.class);
        encryptionManager.run();

        // C:\\Ben\\Encryptor_messages\\encrypted
        // C:\\Ben\\Encryptor_messages\\encrypted\\key.txt
    }
}