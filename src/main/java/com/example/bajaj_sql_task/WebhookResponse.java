package com.example.bajaj.model;

import lombok.Data;

@Data
public class WebhookResponse {
    private String webhook;
    private String accessToken;
}
