package com.datastax.astra;

import com.datastax.astra.sdk.AstraClient;
import com.datastax.oss.driver.api.core.CqlSession;
import com.dtsx.astra.sdk.org.domain.DefaultRoles;
import com.dtsx.astra.sdk.org.domain.Role;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class QuickStartAstra {
    
    public static final String ASTRA_DB_TOKEN    = "CHANGE_ME";
    public static final String ASTRA_DB_ID       = "CHANGE_ME";
    public static final String ASTRA_DB_REGION   = "CHANGE_ME";
    public static final String ASTRA_DB_KEYSPACE = "CHANGE_ME";
    /*
    public static void main(String[] args) {
        try (AstraClient astraClient = configureAstraClient()) {
            // Devops
            testDevopsStreamingApi(astraClient);
            testDevopsOrganizationApi(astraClient);
            testDevopsDatabaseApi(astraClient);
            testDocumentApi(astraClient);
            testGraphQLApi(astraClient);
            testGrpcApi(astraClient);
            
            System.out.println("============ SUCCESS =================");
        }
    }
    
    public static AstraClient configureAstraClient() {
        return AstraClient.builder()
         .withToken(ASTRA_DB_TOKEN)
         .withDatabaseId(ASTRA_DB_ID)
         .withDatabaseRegion(ASTRA_DB_REGION)
         .withCqlKeyspace(ASTRA_DB_KEYSPACE)
         .enableCql()
         .enableGrpc()
         .build();
    }
    
    public static void testDevopsOrganizationApi(AstraClient astraClient) {
        System.out.println("\n[DEVOPS/ORGANIZATION]");
        Optional<Role> role = astraClient.apiDevopsOrganizations().findRoleByName(DefaultRoles.ORGANIZATION_ADMINISTRATOR.getName());
        System.out.println("+ Organization Administrator Role id=" + role.get().getId());
    }
    
    public static void testDevopsStreamingApi(AstraClient astraClient) {
        System.out.println("\n[DEVOPS/STREAMING]");
        
        Map<String, List<String>> clouds = astraClient.apiDevopsStreaming().providers();
        System.out.println("+ Available Clouds to create tenants=" + clouds);
    }
    
    public static void testDevopsDatabaseApi(AstraClient astraClient) {
        System.out.println("\n[DEVOPS/DATABASE]");
        astraClient.apiDevopsDatabases().databaseByName("quickstart").find().ifPresent(db -> {
            System.out.println("+ databaseId=" + db.getId());
            System.out.println("+ databaseRegion=" + db.getInfo().getRegion());
            System.out.println("+ keyspace=" + db.getInfo().getKeyspace());
        });
    }


    
    public static void testDocumentApi(AstraClient astraClient) {
        System.out.println("\n[STARGATE/DOCUMENT]");
        System.out.println("+ Namespaces (doc)    : " + astraClient.apiStargateDocument()
            .namespaceNames().collect(Collectors.toList()));
    }
    
    public static void testGraphQLApi(AstraClient astraClient) {
        System.out.println("\n[STARGATE/GRAPHQL]");
        System.out.println("+ Keyspaces (graphQL) : " + astraClient.apiStargateGraphQL().keyspaceDDL().keyspaces());
    }
    
    public static void testGrpcApi(AstraClient astraClient) {
        System.out.println("\n[STARGATE/GRPC]");
        System.out.println("+ Cql Version (grpc)  : " + astraClient.apiStargateGrpc().execute("SELECT cql_version from system.local")
                .one().getString("cql_version"));
    }
    
   */
    
}
