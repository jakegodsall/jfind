package com.jakegodsall.cli;

import java.nio.file.Path;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CliHandler {
    private class CliResult {
        private final Path path;
        private final String name;
        private final char type;
        private final int maxDepth;
        private final boolean hidden;
        private final boolean print0;

        public CliResult(
            Path path, String name, char type, int maxDepth, boolean hidden, boolean print0
        ) {
            this.path = path;
            this.name = name;
            this.type = type;
            this.maxDepth = maxDepth;
            this.hidden = hidden;
            this.print0 = print0;
        }
    }

    public void parse(String[] args) {
        CommandLineParser parser = new DefaultParser();
        
        try {
            CommandLine cli = parser.parse(getOptions(), args);

            
        } catch (ParseException ex) {
            System.out.println("Error parsing the provided arguments: " + ex.getMessage());
        }
    }

    private CliResult validate(CommandLine cli) {

        String[] remainder = cli.getArgs();
        if (remainder.length != 1) {
            throw new IllegalArgumentException("Only a single path can be passed");
        }
        Path path = Path.of(remainder[0]);

        String name = cli.getOptionValue("name");
        
        String typeRaw = cli.getOptionValue("type");
        if (typeRaw.toLowerCase() !== "f" || typeRaw.toLowerCase() !== "d") {
            throw new IllegalArgumentException("The type must be either f or d");
        }
        char type = Character.toLowerCase(typeRaw.charAt(0));

        int maxDepth = cli.getOptionCount("maxdepth");
        if (maxDepth < 0) {
            throw new IllegalArgumentException("Max depth must be a positive integer");
        }

        boolean hidden = cli.hasOption("hidden");
        boolean print0 = cli.hasOption("print0");

        return new CliResult(path, name, type, maxDepth, hidden, print0);
    }

    private Options getOptions() {
        Options options = new Options();

        options.addOption(
            Option.builder("name")
                .hasArg()
                .get()
        );

        options.addOption(
          Option.builder("type")
            .hasArg()
            .get()  
        );

        options.addOption(
            Option.builder("maxdepth")
                .hasArg()
                .get()
        );

        options.addOption(
            Option.builder("hidden")
                .get()
        );

        options.addOption(
            Option.builder("print0")
                .get()
        );

        return options;
    }
}
