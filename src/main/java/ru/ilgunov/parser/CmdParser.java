package ru.ilgunov.parser;

import java.nio.file.Path;

public class CmdParser {

    public CmdArgument parse(String[] args) {
        CmdArgument result = new CmdArgument();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-a" -> result.isAppend = true;
                case "-s" -> result.shortStats = true;
                case "-f" -> result.fullStats = true;
                case "-p" -> result.prefix = args[++i];
                case "-o" -> result.outputDir = Path.of(args[++i]);
                default -> result.inputDirs.add(Path.of(args[i]));
            }
        }
        return result;
    }
}
