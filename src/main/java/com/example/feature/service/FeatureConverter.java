package com.example.feature.service;

import com.example.feature.adapter.http.v1.dto.FeatureDto;
import com.example.feature.adapter.http.v1.dto.FeaturesDto;
import com.example.feature.model.Feature;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class FeatureConverter {

    public FeatureDto toFeatureDto(Feature feature) {
        val properties = feature.getProperties();
        val acquisition = properties.getAcquisition();
        return FeatureDto.builder()
                .id(properties.getId())
                .timestamp(properties.getTimestamp())
                .missionName(acquisition.getMission())
                .beginViewingDate(acquisition.getBeginViewingDate())
                .endViewingDate(acquisition.getEndViewingDate())
                .build();
    }

    public FeaturesDto toFeaturesDto(List<Feature> features) {
        return FeaturesDto.builder()
                .features(toFeatureDto(features))
                .build();
    }

    private List<FeatureDto> toFeatureDto(List<Feature> features) {
        return features.stream()
                .map(this::toFeatureDto)
                .collect(toList());
    }

}
