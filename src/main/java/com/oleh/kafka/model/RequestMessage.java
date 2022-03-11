package com.oleh.kafka.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class RequestMessage {
    @JsonProperty
    private String date;

    @JsonProperty
    private double amount;
}
