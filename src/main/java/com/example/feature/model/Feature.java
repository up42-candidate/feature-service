package com.example.feature.model;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class Feature {
    private String type;
    private Properties properties;

    @Data
    public static class Properties {
        private UUID id;
        private Acquisition acquisition;
        private OffsetDateTime timestamp;
        private byte[] quicklook;

        @Data
        public static class Acquisition {
            private String mission;
            private OffsetDateTime endViewingDate;
            private OffsetDateTime beginViewingDate;
        }
    }

}
