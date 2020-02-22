package com.example.feature.service;

import com.example.feature.adapter.http.v1.dto.FeatureDto;
import com.example.feature.exception.FeatureNotFoundException;
import com.example.feature.exception.QuicklookNotFoundException;
import com.example.feature.model.Feature;
import com.example.feature.repository.FeatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class FeatureService {

    private final FeatureRepository featureRepository;

    private final FeatureConverter featureConverter;

    public FeatureDto getById(UUID id) {
        return featureConverter.toFeatureDto(getFeature(id));
    }

    private Feature getFeature(UUID id) {
        return featureRepository.getById(id)
                .orElseThrow(() -> new FeatureNotFoundException(id));
    }

    public byte[] getQuickLookById(UUID id) {
        return ofNullable(getFeature(id).getProperties().getQuicklook())
                .orElseThrow(() -> new QuicklookNotFoundException(id));
    }

}
