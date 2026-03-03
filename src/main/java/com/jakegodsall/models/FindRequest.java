package com.jakegodsall.models;

import java.nio.file.Path;

public record FindRequest(
    Path path,
    String name,
    FileType type,
    int maxDepth,
    boolean includeHidden,
    boolean print0
) {}