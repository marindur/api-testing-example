package api;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class ValidateEndpointsTest {

    private String pageId;
    private String componentId;

    // ---------------------------------------- Basic tests ----------------------------------------

    // Test the /v1/pages endpoint
    @Test(priority = 1)
    public void testGetPages() {

        Response response = Endpoints.getPages();
        // Verify status code is 200
        Assert.assertEquals(response.getStatusCode(),200);

        // Extract the list of pages IDs
        List<String> pagesIds = response.jsonPath().getList("id");
        // Verify list of pages IDs is not null and not empty
        Assert.assertNotNull(pagesIds, "List of pages IDs should not be null.");
        Assert.assertFalse(pagesIds.isEmpty(), "List of pages IDs should not be empty.");

        // Save ID of first page for subsequent tests
        pageId = pagesIds.getFirst();
    }

    // Test the /v1/:page_id/components endpoint
    @Test(priority = 2, dependsOnMethods = {"testGetPages"})
    public void testGetComponents() {

        Response response = Endpoints.getComponents(pageId);
        // Verify status code is 200
        Assert.assertEquals(response.getStatusCode(),200);

        // Extract the list of components IDs
        List<String> componentsIds = response.jsonPath().getList("id");
        // Verify list of components IDs is not null and not empty
        Assert.assertNotNull(componentsIds, "List of components IDs should not be null.");
        Assert.assertFalse(componentsIds.isEmpty(), "List of components IDs should not be empty.");

        // Save ID of first component for subsequent tests
        componentId = componentsIds.getFirst();
    }

    // Test the /v1/:page_id/components/:component_id endpoint
    @Test(priority = 3, dependsOnMethods = {"testGetPages", "testGetComponents"})
    public void testGetComponentData() {

        Response response = Endpoints.getComponentData(pageId, componentId);
        // Verify status code is 200
        Assert.assertEquals(response.getStatusCode(),200);

        // Validate that component ID from response is the same as expected component ID
        Assert.assertEquals(response.jsonPath().getString("id"), componentId, "Component ID mismatch");
    }

    // Test the /v1/:page_id/incidents endpoint
    @Test(priority = 4, dependsOnMethods = {"testGetPages"})
    public void testGetIncidents() {

        Response response = Endpoints.getIncidents(pageId);
        // Verify status code is 200
        Assert.assertEquals(response.getStatusCode(),200);

        // Extract the list of incidents IDs
        List<String> incidentsIds = response.jsonPath().getList("id");
        // Verify list of incidents IDs is not null and not empty
        Assert.assertNotNull(incidentsIds, "List of incidents IDs should not be null.");
        Assert.assertFalse(incidentsIds.isEmpty(), "List of incidents IDs should not be empty.");
    }


    // ---------------------------------------- Additional tests ----------------------------------------

    // Test status of site for component of incident
    @Test(priority = 5, dependsOnMethods = {"testGetPages"}, dataProvider = "incidentStatus", dataProviderClass = TestData.class)
    public void testIncidentSiteStatus(String expectedStatus) {

        Response response = Endpoints.getIncidents(pageId);
        // Verify status code is 200
        Assert.assertEquals(response.getStatusCode(),200);

        // Extract the status of site for component of first incident
        String status = response.jsonPath().getString("components[0].site[0].status");
        // Verify status of site for component of first incident is not null and is not empty
        Assert.assertNotNull(status, "Status should not be null.");
        Assert.assertFalse(status.isEmpty(), "Status should not be empty.");

        // Validate that status from response is the same as expected status
        Assert.assertEquals(status, expectedStatus,"Data are not the same.");
    }

    // Test names of all components
    @Test(priority = 6, dependsOnMethods = {"testGetPages"}, dataProvider = "componentsNamesData", dataProviderClass = TestData.class)
    public void testComponentsNames(List<String> expectedComponentsNames) {

        Response response = Endpoints.getComponents(pageId);
        // Verify status code is 200
        Assert.assertEquals(response.getStatusCode(),200);

        // Extract the list of names for all components
        List<String> componentsNames = response.jsonPath().getList("name");
        // Verify list of names for all components is not null and not empty
        Assert.assertNotNull(componentsNames, "List of components names should not be null.");
        Assert.assertFalse(componentsNames.isEmpty(), "List of components names should not be empty.");

        // Validate that list of components names from response is the same as expected list of components names
        Assert.assertEquals(componentsNames, expectedComponentsNames,"Data are not the same.");
    }

    // Test the /v1/:page_id/components endpoint with invalid page ID
    @Test(priority = 7, dataProvider = "invalidPageId", dataProviderClass = TestData.class)
    public void testInvalidPageId(String invalidPageId) {

        Response response = Endpoints.getComponents(invalidPageId);
        // Verify status code is 404
        Assert.assertEquals(response.getStatusCode(),404);

        // Validate that response contain expected text
        Assert.assertTrue(response.getBody().asString().contains("page_not_found"), "Expected 404 Not Found");
    }

    // Test the /v1/:page_id/components endpoint with invalid component ID
    @Test(priority = 8, dependsOnMethods = {"testGetPages"}, dataProvider = "invalidComponentId", dataProviderClass = TestData.class)
    public void testInvalidComponentId(String invalidComponentId) {

        Response response = Endpoints.getComponentData(pageId, invalidComponentId);
        // Verify status code is 500
        Assert.assertEquals(response.getStatusCode(),500);

        // Validate that response contain expected text
        Assert.assertTrue(response.getBody().asString().contains("error_occurred"), "500 Internal Server Error");
    }

    // Test unauthorized access without a bearer token
    @Test(priority = 9)
    public void testUnauthorizedAccess() {

        Response response = Endpoints.getPagesWithoutToken();
        // Verify status code is 401
        Assert.assertEquals(response.getStatusCode(),401);

        // Validate that response contain expected text
        Assert.assertTrue(response.getBody().asString().contains("forbidden"), "Expected 401 Unauthorized");
    }

    // Test unauthorized access with invalid bearer token
    @Test(priority = 10,  dataProvider = "invalidBearerToken", dataProviderClass = TestData.class)
    public void testInvalidBearerToken(String invalidBearerToken) {

        Response response = Endpoints.getPagesWithInvalidToken(invalidBearerToken);
        // Verify status code is 401
        Assert.assertEquals(response.getStatusCode(),401);

        // Validate that response contain expected text
        Assert.assertTrue(response.getBody().asString().contains("forbidden"), "Expected 401 Unauthorized");
    }
}
