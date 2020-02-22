package com.example.feature.repository;

import com.example.feature.model.Feature;

import java.util.Optional;
import java.util.UUID;

public interface FeatureRepository {
    Optional<Feature> getById(UUID id);
}
