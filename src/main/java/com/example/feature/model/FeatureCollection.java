package com.example.feature.model;

import lombok.Data;

import java.util.List;

@Data
public class FeatureCollection {
    private String type;
    private List<Feature> features;
}
