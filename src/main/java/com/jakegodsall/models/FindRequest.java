package com.jakegodsall.models;

import java.nio.file.Path;
import java.util.Optional;
import com.jakegodsall.enums.FileType;

public record FindRequest(
    Path path,
    Optional<String> name,
    FileType type,
    int maxDepth,
    boolean includeHidden,
    boolean print0
) {}