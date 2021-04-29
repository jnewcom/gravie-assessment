package assessment.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GiantBombConfiguration {

    @Value("${giant.bomb.api.url}")
    private String url;
    @Value("${giant.bomb.api.key}")
    private String key;

    public String getUrl() {
        return url;
    }

    public String getKey() {
        return key;
    }
}
