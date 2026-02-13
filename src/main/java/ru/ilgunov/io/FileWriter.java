package ru.ilgunov.io;

import ru.ilgunov.detector.DataType;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class FileWriter {
    private final Path outputDir;
    private final boolean append;
    private final String prefix;
    private final Map<DataType, BufferedWriter> writers = new HashMap();

    public FileWriter(Path outputDir, boolean append, String prefix) {
        this.outputDir = outputDir;
        this.append = append;
        this.prefix = prefix;
    }

    public void writeToFile(DataType type, String value) {
        try {
            BufferedWriter wr = writers.get(type);
            if (wr == null) {
                wr = createWriter(type);
                writers.put(type, wr);
            }
            wr.write(value);
            wr.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedWriter createWriter(DataType type) throws IOException {
        String fileName = prefix + switch (type) {
            case INTEGER -> "integer.txt";
            case FLOAT -> "float.txt";
            case STRING -> "string.txt";
        };

        Path path = outputDir.resolve(fileName);
        OpenOption[] options;

        if (append) {
            options = new OpenOption[] {
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            };
        } else {
            options = new OpenOption[] {
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            };
        }
        return Files.newBufferedWriter(path, options);
    }

    public void closeAll() {
        writers.values().forEach(wr -> {
            try {
                wr.close();
            } catch (IOException ignored) {}
        });
    }
}
