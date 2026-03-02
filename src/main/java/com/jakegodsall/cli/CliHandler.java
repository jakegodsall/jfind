package com.jakegodsall.cli;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CliHandler {
    public void parse(String[] args) {
        CommandLineParser parser = new DefaultParser();
        
        try {
            parser.parse(getOptions(), args);
        } catch (ParseException ex) {
            System.out.println("Error parsing the provided arguments: " + ex.getMessage());
        }
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
