package com.jakegodsall.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import com.jakegodsall.models.FindRequest;

public class FindService {
    public static void listAll(FindRequest request) {
        Path p = request.path();

        try (Stream<Path> paths = Files.list(p)) {
            paths.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Error listing objects in path: " + p);
        }
    }
}
