package ru.ilgunov.parser;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CmdArgument {
    public boolean isAppend;
    public boolean shortStats;
    public boolean fullStats;
    public String prefix = "";
    public Path outputDir = Path.of(".");
    public List<Path> inputDirs = new ArrayList<>();
}
