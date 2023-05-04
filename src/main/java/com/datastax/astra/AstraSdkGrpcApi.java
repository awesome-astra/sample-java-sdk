package com.datastax.astra;

import com.datastax.astra.sdk.AstraClient;
import io.stargate.sdk.grpc.StargateGrpcApiClient;

public class AstraSdkGrpcApi {

    public static void main(String[] args) {

        // Connect
        try (AstraClient astraClient = AstraClient.builder()
                .withClientId("client_id")
                .withClientSecret("client_secret")
                .withDatabaseId("database_id")
                .withDatabaseRegion("database_region")
                .build()) {

            StargateGrpcApiClient grpcClient = astraClient.apiStargateGrpc();
            System.out.println("+ Cql Version (grpc)  : " + grpcClient
                    .execute("SELECT cql_version from system.local")
                    .one().getString("cql_version"));
        }

    }
}
