package com.example.feature.service;

import com.example.feature.adapter.http.v1.dto.FeatureDto;
import com.example.feature.model.Feature;
import lombok.val;
import org.springframework.stereotype.Component;

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

}
