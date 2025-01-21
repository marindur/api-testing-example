package config;

import org.yaml.snakeyaml.Yaml;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ConfigReader {

    public static String getBearerToken() throws IOException {
        // Load the YAML file
        InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.yaml");

        if (inputStream == null) {
            throw new IOException("YAML file not found!");
        }

        // Parse the YAML content
        Yaml yaml = new Yaml();
        Map<String, Object> data = yaml.load(inputStream);

        // Retrieve and return the bearer token
        return (String) data.get("bearerToken");
    }
}
