package com.example.feature.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.util.UUID;

import static java.lang.String.format;

public class QuicklookNotFoundException extends AbstractThrowableProblem {
    public QuicklookNotFoundException(UUID id) {
        super(null, "Quicklook Not Found", Status.NOT_FOUND, format("Quicklook %s is not found!", id));
    }
}
