package com.jakegodsall.cli;

import java.nio.file.Path;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.jakegodsall.models.FileType;
import com.jakegodsall.models.FindRequest;


public class CliHandler {
    public FindRequest parseRequest(String[] args) {
        CommandLineParser parser = new DefaultParser();
        
        try {
            CommandLine cli = parser.parse(getOptions(), args);
            return validate(cli);
        } catch (ParseException ex) {
            System.out.println("Error parsing the provided arguments: " + ex.getMessage());
            return null;
        }
    }

    private FindRequest validate(CommandLine cli) {

        String[] remainder = cli.getArgs();
        if (remainder.length != 1) {
            throw new IllegalArgumentException("Only a single path can be passed");
        }
        Path path = Path.of(remainder[0]);

        String name = cli.getOptionValue("name");
        
        FileType type = parseType(cli.getOptionValue("type"));

        int maxDepth = parseDepth(cli.getOptionValue("maxdepth"));

        boolean hidden = cli.hasOption("hidden");
        boolean print0 = cli.hasOption("print0");

        return new FindRequest(path, name, type, maxDepth, hidden, print0);
    }

    private int parseDepth(String raw) {
        try {
            int val = Integer.parseInt(raw);
            if (val < 0) {
                throw new IllegalArgumentException("Max depth must be a positive integer");
            }

            return val;
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("Non-numeric value passed to maxdepth");
        }
    }

    private FileType parseType(String raw) {
        FileType type = FileType.FILE;
        
        if (raw != null) {
            switch (raw.toLowerCase()) {
                case "d":
                    type = FileType.DIRECTORY;
                    break;
                case "f":
                    type = FileType.FILE;
                    break;
            }
        }
        return type;
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
