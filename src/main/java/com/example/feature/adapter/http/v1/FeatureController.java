package com.example.feature.adapter.http.v1;

import com.example.feature.adapter.http.v1.dto.FeatureDto;
import com.example.feature.service.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FeatureController {

    private final FeatureService featureService;

    @GetMapping(path = "/features/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public FeatureDto getById(@PathVariable UUID id) {
        return featureService.getById(id);
    }

    @GetMapping(path = "/features/{id}/quicklook", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImageById(@PathVariable UUID id) {
        return featureService.getQuickLookById(id);
    }

}
