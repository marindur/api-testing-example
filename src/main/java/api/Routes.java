package api;

public class Routes {

    private static final String BASE_URL = "https://api.instatus.com";
    public static final String BASE_URL_V1 = BASE_URL + "/v1/";

    public static String getPages = BASE_URL_V1 + "pages";
    public static String getComponents = BASE_URL_V1 + "{id}" + "/components";
    public static String getComponentData = BASE_URL_V1 + "{id}" + "/components/" + "{id}";
    public static String getIncidents = BASE_URL_V1 + "{id}" + "/incidents";

}
