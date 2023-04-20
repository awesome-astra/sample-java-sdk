package com.datastax.astra;

import com.datastax.astra.sdk.AstraClient;
import com.datastax.oss.driver.api.core.CqlSession;

public class AstraSdkDrivers {
    public static void main(String[] args) {
        try (AstraClient astraClient = AstraClient.builder()
                .withClientId("client_id")
                .withClientSecret("client_secret")
                .withDatabaseId("database_id")
                .withDatabaseRegion("database_region")
                .withCqlKeyspace("demo")
                .enableCql()
                .build()) {

            try(CqlSession cqlSession = astraClient.cqlSession()) {
                System.out.println("+ Cql Version (cql)   : " + cqlSession
                        .execute("SELECT cql_version from system.local")
                        .one().getString("cql_version"));
            }
        }
    }
}
