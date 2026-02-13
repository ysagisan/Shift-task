package ru.ilgunov.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

public class InputFileReader {

    public void readFile(Path path, Consumer<String> consumer) {
        try (BufferedReader bf = Files.newBufferedReader(path)) {
            String line;
            while ((line = bf.readLine()) != null ) {
                consumer.accept(line);
            }
        } catch (IOException e) {
            System.err.println("Cannot read file" + path.toString());
        }
    }
}
