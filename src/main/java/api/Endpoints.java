package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class Endpoints {

    private static final String BEARER_TOKEN = "8d16333936e72e705980878e18c95976";

    // Creates the base request specification to be used by all API calls
    private static RequestSpecification baseRequestSpec() {
        return given()
                .header("accept", "application/json")
                .contentType(ContentType.JSON);
    }

    // Sends a GET request to retrieve pages data
    public static Response getPages() {
        return baseRequestSpec()
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .when()
                .get(Routes.getPages);
    }

    // Sends a GET request to retrieve components data using page ID
    public static Response getComponents(String id) {
        return baseRequestSpec()
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .pathParams("id", id)
                .when()
                .get(Routes.getComponents);
    }

    // Sends a GET request to retrieve component data of specific component using page ID and component ID
    public static Response getComponentData(String pId, String cId) {
        return baseRequestSpec()
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .pathParams("id", pId)
                .pathParams("id", cId)
                .when()
                .get(Routes.getComponentData);
    }

    // Sends a GET request to retrieve incidents data using page ID
    public static Response getIncidents(String id) {
        return baseRequestSpec()
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .pathParams("id", id)
                .when()
                .get(Routes.getIncidents);
    }

    // Sends a GET request to retrieve pages data without any authentication (no Bearer token)
    public static Response getPagesWithoutToken() {
        return baseRequestSpec()
                .when()
                .get(Routes.getPages);
    }

    // Sends a GET request to retrieve pages with an invalid Bearer token for authentication
    public static Response getPagesWithInvalidToken(String invalidToken) {
        return baseRequestSpec()
                .header("Authorization", "Bearer " + invalidToken)
                .when()
                .get(Routes.getPages);
    }
}
