package com.example.feature.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.util.UUID;

import static java.lang.String.format;

public class FeatureNotFoundException extends AbstractThrowableProblem {
    public FeatureNotFoundException(UUID id) {
        super(null, "Feature Not Found", Status.NOT_FOUND, format("Feature %s is not found!", id));
    }
}
