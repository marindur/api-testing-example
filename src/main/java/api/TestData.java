package api;

import org.testng.annotations.DataProvider;
import java.util.Arrays;
import java.util.List;

public class TestData {

    @DataProvider(name = "incidentStatus")
    public Object[][] getIncidentStatus() {
        return new Object[][] {
                {"HASISSUES"}
        };
    }

    @DataProvider(name = "componentsNamesData")
    public Object[][] getComponentsNamesData() {
        List<String> expectedComponentsNames = Arrays.asList("Website", "App", "Buggy Component",
                "Operational Component", "Operational Component 2");
        return new Object[][] {
                {expectedComponentsNames}
        };
    }

    @DataProvider(name = "invalidPageId")
    public Object[][] getInvalidPageId() {
        return new Object[][] {
                {"cl2pxld6h45905iln8lwv81ai3"},
                {"cl2abg46h00747lol8lnl09la1"}
        };
    }

    @DataProvider(name = "invalidComponentId")
    public Object[][] getInvalidComponentId() {
        return new Object[][] {
                {"cl2pxld7l90028iln8fmkpzy3z"},
                {"cl7kola7l75839uvn7kun00g9n"}
        };
    }

    @DataProvider(name = "invalidBearerToken")
    public Object[][] getInvalidBearerToken() {
        return new Object[][] {
                {"8d16333936e72e000980878e18c95976"},
                {"8d16333936e72e333980878e18c95976"}
        };
    }
}
