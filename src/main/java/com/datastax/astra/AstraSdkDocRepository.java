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

        try (AstraClient astraClient = AstraClient.builder()
                .withClientId("token")
                .withClientSecret("AstraCS:uZclXTYecCAqPPjiNmkezapR:e87d6edb702acd87516e4ef78e0c0e515c32ab2c3529f5a3242688034149a0e4")
                .withDatabaseId("3043a40f-39bf-464e-8337-dc283167b2c3")
                .withDatabaseRegion("us-east1")
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
