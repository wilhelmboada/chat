package com.chat.service.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"error"})
public class ErrorResponse {

    @JsonProperty
    private String error;

    public ErrorResponse(String error) {
        this.error = error;
    }
}
