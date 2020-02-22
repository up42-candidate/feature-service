package com.example.feature.adapter.http.v1.dto;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
public class FeatureDto {

    private UUID id;

    private OffsetDateTime timestamp;

    private OffsetDateTime beginViewingDate;

    private OffsetDateTime endViewingDate;

    private String missionName;

}
