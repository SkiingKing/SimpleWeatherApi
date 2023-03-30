package org.example.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {


    private Integer precip;
    private Integer snow;
    private Double temp;

    @JsonProperty("timestamp_local")
    private Timestamp timestampLocal;

    @JsonProperty("timestamp_utc")
    private Timestamp timestampUtc;

    private Long ts;

}
