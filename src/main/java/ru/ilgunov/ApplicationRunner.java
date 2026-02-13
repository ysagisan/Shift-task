package ru.ilgunov;

import ru.ilgunov.detector.DataType;
import ru.ilgunov.detector.DataTypeDetector;
import ru.ilgunov.io.FileWriter;
import ru.ilgunov.io.InputFileReader;
import ru.ilgunov.parser.CmdArgument;
import ru.ilgunov.parser.CmdParser;
import ru.ilgunov.stats.Stats;
import ru.ilgunov.stats.StatsFactory;

import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Map;

public class ApplicationRunner {
    static Map<DataType, Stats> stats = new EnumMap<>(DataType.class);

    public static void run(String[] args) {
        CmdArgument cmd = new CmdParser().parse(args);
        InputFileReader fileReader = new InputFileReader();
        DataTypeDetector detectorType = new DataTypeDetector();
        FileWriter wr = new FileWriter(cmd.outputDir, cmd.isAppend, cmd.prefix);

        for (Path path : cmd.inputDirs) {
            fileReader.readFile(path, line -> {
                DataType type = detectorType.detectType(line);
                wr.writeToFile(type, line);

                Stats s = stats.get(type);
                if (s == null) {
                    s = StatsFactory.createStats(type, cmd.shortStats, cmd.fullStats);
                    if (s != null) {
                        stats.put(type, s);
                    }
                }
                if (s != null) {
                    s.accept(line);
                }

            });
        }
        wr.closeAll();

        stats.forEach((type, stat) ->{
            System.out.println("******** " + type + " ********");
            stat.print();
        });
    }
}
