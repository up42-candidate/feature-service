package com.example.feature.repository;

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

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class FeatureConfiguration {

    @NonNull
    @Value("classpath:${application.static-data-file}")
    private final Resource data;

    @NonNull
    private final ObjectMapper mapper;

    @Bean
    @SneakyThrows
    FeatureRepository featureRepository() {
        return new FeatureRepository(mapper.readValue(data.getURL(), new TypeReference<List<FeatureCollection>>() {
        }).stream()
                .map(FeatureCollection::getFeatures)
                .flatMap(Collection::stream)
                .collect(toMap(feature -> feature.getProperties().getId(), identity())));
    }

}
