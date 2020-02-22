package com.example.feature.repository;

import com.example.feature.model.Feature;
import com.example.feature.model.FeatureCollection;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class FeatureConfiguration {
    private static final TypeReference<List<FeatureCollection>> FEATURE_LIST_TYPE =
            new TypeReference<List<FeatureCollection>>() {
            };

    @NonNull
    @Value("classpath:${application.static-data-file}")
    private final Resource data;

    @NonNull
    private final ObjectMapper mapper;

    @Bean
    FeatureRepository featureStaticRepository() {
        log.info("Reading static data from {}", data);
        return new FeatureStaticRepository(loadFeatures());
    }

    private Map<UUID, Feature> loadFeatures() {
        return readFeatures().stream()
                .map(FeatureCollection::getFeatures)
                .flatMap(Collection::stream)
                .collect(toMap(this::getFeatureId, identity()));
    }

    private UUID getFeatureId(Feature feature) {
        return feature.getProperties().getId();
    }

    @SneakyThrows
    private List<FeatureCollection> readFeatures() {
        return mapper.readValue(data.getURL(), FEATURE_LIST_TYPE);
    }

}
