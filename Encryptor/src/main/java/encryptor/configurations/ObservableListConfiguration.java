package encryptor.configurations;

import encryptor.listeners.EncryptionObserver;
import encryptor.loggers.EncryptionLogger;
import encryptor.ui.UIManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;

@Configuration
public class ObservableListConfiguration {

    @Autowired
    @Bean("observersList")
    public List<EncryptionObserver> createObserversList(UIManager uiManager) {
        List<EncryptionObserver> observerList = new LinkedList<>();
        observerList.add(new EncryptionLogger(uiManager));
        return observerList;
    }
}
