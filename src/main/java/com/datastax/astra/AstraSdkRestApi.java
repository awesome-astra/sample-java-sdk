package com.datastax.astra;

import com.datastax.astra.sdk.AstraClient;
import io.stargate.sdk.core.domain.RowResultPage;
import io.stargate.sdk.rest.StargateRestApiClient;
import io.stargate.sdk.rest.TableClient;
import io.stargate.sdk.rest.domain.CreateTable;
import io.stargate.sdk.rest.domain.SearchTableQuery;

import java.util.HashMap;
import java.util.Map;

public class AstraSdkRestApi {
    public static void main(String[] args) {
        try (AstraClient astraClient = AstraClient.builder()
                .withClientId("client_id")
                .withClientSecret("client_secret")
                .withDatabaseId("database_id")
                .withDatabaseRegion("database_region")
                .build()) {
            StargateRestApiClient restApiClient =
                    astraClient.apiStargateData();

            // -- Create a table
            CreateTable createTable = CreateTable.builder()
                    .name("my_table")
                    .addPartitionKey("foo", "text")
                    .addColumn("bar", "text")
                    .ifNotExist(true)
                    .build();
            restApiClient.keyspace("demo")
                    .createTable("my_table", createTable);

            // we can now work on the table
            TableClient tableClient = restApiClient
                    .keyspace("demo")
                    .table("my_table");

            // -- Insert a row
            Map<String, Object> record = new HashMap<>();
            record.put("foo", "Hello");
            record.put("bar", "World");
            tableClient.upsert(record);

            // -- Retrieve rows
            SearchTableQuery query = SearchTableQuery.builder().
                    select("foo", "bar")
                    .where("foo")
                    .isEqualsTo("Hello")
                    .build();
            RowResultPage result = tableClient.search(query);
            System.out.println(result.getResults()
                    .get(0).get("bar"));
        }
    }
}
