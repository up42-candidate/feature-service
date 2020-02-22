package com.example.feature.service;

import com.example.feature.adapter.http.v1.dto.FeatureDto;
import com.example.feature.exception.FeatureNotFoundException;
import com.example.feature.repository.FeatureRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FeatureService {

    private final FeatureRepository featureRepository;

    public FeatureDto getById(@NonNull UUID id) {
        val properties = featureRepository.getById(id)
                .orElseThrow(() -> new FeatureNotFoundException(id)).getProperties();
        val acquisition = properties.getAcquisition();
        return FeatureDto.builder()
                .id(properties.getId())
                .timestamp(properties.getTimestamp())
                .missionName(acquisition.getMission())
                .beginViewingDate(acquisition.getBeginViewingDate())
                .endViewingDate(acquisition.getEndViewingDate())
                .build();
    }

}
