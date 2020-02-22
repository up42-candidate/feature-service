package com.example.feature.repository;

import com.example.feature.model.Feature;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
public class FeatureStaticRepository implements FeatureRepository {

    @NonNull
    private final Map<UUID, Feature> features;

    @Override
    public Optional<Feature> getById(@NonNull UUID id) {
        return ofNullable(features.get(id));
    }

}
