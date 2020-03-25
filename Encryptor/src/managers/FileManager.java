package managers;

import exceptions.ReadingFromFileException;
import exceptions.WritingToFileException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {
    public void writeToFile(String pathToFile, String text) throws IOException {
        File file = new File(pathToFile);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            throw new WritingToFileException("Error writing to file " + pathToFile);
        }
    }

    public String readFile(String pathToFile) throws IOException {
        try {
            return new String(Files.readAllBytes(Paths.get(pathToFile)));
        } catch (IOException e) {
            throw new ReadingFromFileException("Error reading from file " + pathToFile);
        }
    }
}
