package com.datastax.astra;

import com.datastax.astra.sdk.AstraClient;
import io.stargate.sdk.doc.CollectionClient;
import io.stargate.sdk.doc.Document;
import io.stargate.sdk.doc.StargateDocumentApiClient;

import java.util.stream.Collectors;

public class AstraSdkDocApi {

    // Given a Java POJO
    public static final class User {
        String email;
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }

    public static void main(String[] args) {

        try (AstraClient astraClient = AstraClient.builder()
                .withClientId("client_id")
                .withClientSecret("client_secret")
                .withDatabaseId("database_id")
                .withDatabaseRegion("database_region")
                .build()) {

            StargateDocumentApiClient apiDoc =
                    astraClient.apiStargateDocument();

            // List namespaces
            System.out.println("- List namespaces  : " + apiDoc
                            .namespaceNames()
                            .collect(Collectors.toList()));

            // List Collection in a Keyspace
            System.out.println("- List collections : " + apiDoc
                    .namespace("demo")
                    .collectionNames()
                    .collect(Collectors.toList()));

            // Create collection
            CollectionClient userCollection = apiDoc
                    .namespace("demo")
                    .collection("user");
            if (!userCollection.exist()) userCollection.create();

            // Working with documents
            User userA = new User();userA.setEmail("a@a.com");
            User userB = new User();userB.setEmail("b@b.com");

            // Create a Document and let Astra create the ID
            String userADocumentId = userCollection.create(userA);
            System.out.println("Document Created: " + userADocumentId);

            // Create a Document with a specific ID
            userCollection.document("b@b.com").upsert(userB);

            // List Documents
            System.out.println("Documents:");
            for(Document<User> doc : userCollection.findPage(User.class).getResults()) {
                System.out.println("" +
                        "- id: " + doc.getDocumentId() +
                        ", email : " + doc.getDocument().getEmail());
            }
        }
    }



}