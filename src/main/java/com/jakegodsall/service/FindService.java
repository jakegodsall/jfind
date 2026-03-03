package com.jakegodsall.service;

import java.nio.file.Files;
import java.nio.file.Path;

import com.jakegodsall.models.FindRequest;

public class FindService {
    public static void listAll(FindRequest request) {
        Path p = request.path();
        System.out.println(Files.isDirectory(p));
    }
}
