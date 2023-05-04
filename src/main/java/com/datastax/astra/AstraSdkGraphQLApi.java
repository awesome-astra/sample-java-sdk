package com.datastax.astra;

import com.datastax.astra.sdk.AstraClient;
import io.stargate.sdk.gql.StargateGraphQLApiClient;

public class AstraSdkGraphQLApi {

    public static void main(String[] args) {

        try (AstraClient astraClient = AstraClient.builder()
                .withClientId("client_id")
                .withClientSecret("client_secret")
                .withDatabaseId("database_id")
                .withDatabaseRegion("database_region")
                .build()) {

            StargateGraphQLApiClient graphClient = astraClient.apiStargateGraphQL();
            graphClient.keyspaceDDL().keyspaces().forEach(k-> System.out.println(k.getName()));
        }
    }
}
