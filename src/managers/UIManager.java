package managers;

import models.UserOptions;

import java.util.Scanner;

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
        int choice = 0;
        try {
            choice = Integer.valueOf(getUserInput("Hello user!\nPress 1 for encryption\nPress 2 for decryption\nPress 3 to exit"));
        } catch (NumberFormatException e) {
            System.err.println("Could not parse your choice. Please enter a number again.");
        }
        return UserOptions.getOptionByCodeNumber(choice);
    }

    private String getUserInput(String messageToPrint) {
        System.out.println(messageToPrint);
        return scanner.nextLine();
    }
}
