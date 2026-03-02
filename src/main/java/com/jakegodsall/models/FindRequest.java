package com.jakegodsall.models;

public record FindRequest(
    Path path,
    Optional<String> name,
    FileType type,
    int maxDepth,
    boolean includeHidden,
    boolean print0
) {}