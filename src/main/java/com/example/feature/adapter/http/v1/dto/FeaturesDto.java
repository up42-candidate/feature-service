package com.example.feature.adapter.http.v1.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FeaturesDto {
    private List<FeatureDto> features;
}
