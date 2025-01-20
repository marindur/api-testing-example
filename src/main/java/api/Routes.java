package api;

public class Routes {

    private static final String BASE_URL = "https://api.instatus.com";
    public static final String PAGES_PATH = "/v1/pages";

    public static String getPages = BASE_URL + PAGES_PATH;
    public static String getComponents = BASE_URL + "/v1/" + "{id}" + "/components";
    public static String getComponentData = BASE_URL + "/v1/" + "{id}" + "/components/" + "{id}";
    public static String getIncidents = BASE_URL + "/v1/" + "{id}" + "/incidents";

}
