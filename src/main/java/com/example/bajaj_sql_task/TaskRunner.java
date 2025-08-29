package com.example.bajaj.service;

import com.example.bajaj.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TaskRunner implements CommandLineRunner {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void run(String... args) throws Exception {
        // Step 1: Call generateWebhook API
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
        WebhookRequest requestBody = new WebhookRequest("John Doe", "REG12347", "john@example.com");

        ResponseEntity<WebhookResponse> response =
                restTemplate.postForEntity(url, requestBody, WebhookResponse.class);

        WebhookResponse webhookResponse = response.getBody();
        if (webhookResponse == null) {
            //System.out.println("❌ Failed to get webhook response");
            return;
        }

        //System.out.println("✅ Webhook URL: " + webhookResponse.getWebhook());
        //System.out.println("✅ Access Token: " + webhookResponse.getAccessToken());

        // Step 2: SQL Solution (single-line string)
        String finalQuery =
            "SELECT e1.emp_id, e1.first_name, e1.last_name, d.department_name, " +
            "(SELECT COUNT(*) FROM employee e2 WHERE e2.department = e1.department AND e2.dob > e1.dob) AS younger_employees_count " +
            "FROM employee e1 JOIN department d ON e1.department = d.department_id " +
            "ORDER BY e1.emp_id DESC;";

        FinalQueryRequest finalQueryRequest = new FinalQueryRequest(finalQuery);

        // Step 3: Submit SQL with JWT token
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + webhookResponse.getAccessToken()); // ✅ explicit header

        HttpEntity<FinalQueryRequest> entity = new HttpEntity<>(finalQueryRequest, headers);

        try {
            ResponseEntity<String> submitResponse =
                    restTemplate.postForEntity(webhookResponse.getWebhook(), entity, String.class);

           // System.out.println("✅ Submission Response: " + submitResponse.getBody());
        } catch (Exception e) {
            //System.out.println("❌ Error submitting final query: " + e.getMessage());
        }
    }
}
