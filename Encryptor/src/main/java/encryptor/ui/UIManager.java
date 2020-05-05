package encryptor.ui;

import org.springframework.stereotype.Component;
import encryptor.ui.api.UserOptions;

import java.util.Scanner;

@Component
public class UIManager {
    private Scanner scanner;

    public UIManager() {
        this.scanner = new Scanner(System.in);
    }

    public String getKeyPath() {
        return getUserInput("Enter key's path you want to decrypt with");
    }

    public String getMessagePath() {
        return getUserInput("Enter message's path you want to encrypt");
    }

    public String getCipherPath() {
        return getUserInput("Enter message's path you want to decrypt");
    }

    public UserOptions getChoice() {
        int choice = UserOptions.DEFAULT.getValue();
        try {
            choice = Integer.valueOf(getUserInput(
                    buildMenuMessage()));
        } catch (NumberFormatException e) {
            System.err.println("Could not parse your choice. Please enter a number again.");
        }
        return UserOptions.getOptionByCodeNumber(choice);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printError(String message) {
        System.err.println(message);
    }

    private String buildMenuMessage() {
        StringBuilder menuMessage = new StringBuilder("Hello user!");
        UserOptions[] options = UserOptions.values();
        for(UserOptions option : options) {
            if(option.isPrintable())
                menuMessage.append("\nPress " + option.getValue() + " for " + option.name().toLowerCase());
        }
        return menuMessage.toString();
    }

    private String getUserInput(String messageToPrint) {
        System.out.println(messageToPrint);
        return scanner.nextLine();
    }
}
