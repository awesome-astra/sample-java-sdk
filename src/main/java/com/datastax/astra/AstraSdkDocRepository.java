package com.datastax.astra;

import com.datastax.astra.sdk.AstraClient;
import io.stargate.sdk.doc.StargateDocumentRepository;

public class AstraSdkDocRepository {

    // Given a Java POJO
    public static final class User {
        String email;
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }

    public static void main(String[] args) {

        // Connect
        try (AstraClient astraClient = AstraClient.builder()
                .withClientId("client_id")
                .withClientSecret("client_secret")
                .withDatabaseId("database_id")
                .withDatabaseRegion("database_region")
                .withCqlKeyspace("demo")
                .enableCql()
                .build()) {

            // Doc Repository
            StargateDocumentRepository<User> userRepo =
                    new StargateDocumentRepository<User>(
                            astraClient.apiStargateDocument().namespace("demo"),
                            User.class);

            // Working with documents
            User userA = new User();
            userA.setEmail("a@a.com");
            User userB = new User();
            userB.setEmail("b@b.com");

            // Create Documents
            userRepo.insert(userA);
            userRepo.insert("b@b.com", userB);

            // List Documents
            userRepo.findAll().forEach(doc -> {
                System.out.println(
                        "id=" + doc.getDocumentId()
                        + ", email= " + doc.getDocument().getEmail());
            });

        }
    }
}
