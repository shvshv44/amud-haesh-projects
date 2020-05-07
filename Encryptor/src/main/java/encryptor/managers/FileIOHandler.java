package encryptor.managers;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileIOHandler {

    public void writeToFile(String pathToFile, String text) throws IOException {
        File file = new File(pathToFile);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(text);
        } catch (IOException e) {
            throw new IOException("Error writing to file " + pathToFile);
        }
    }

    public String readFile(String pathToFile) throws IOException {
        try {
            return new String(Files.readAllBytes(Paths.get(pathToFile)));
        } catch (IOException e) {
            throw new IOException("Error reading from file " + pathToFile);
        }
    }
}
