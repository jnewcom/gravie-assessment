package assessment.service;

import assessment.model.Game;
import assessment.model.Resource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
public class GiantBombService {

    private static final String GIANT_BOMB_SEARCH_REQUEST_FORMAT = "%s/search/?api_key=%s&format=json&query=%s&resources=%s";

    private GiantBombConfiguration config;
    private HttpClient httpClient;

    @Autowired
    public GiantBombService(GiantBombConfiguration config) {
        this.config = config;
        this.httpClient = HttpClient.newHttpClient();
    }

    public List<Object> doSearch(String query, Resource resource) throws Exception {
        List<Object> searchResults = new ArrayList<>();

        HttpRequest request = buildRequest(query, resource);
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return handleResponse(response, resource);
        }

        return searchResults;
    }

    private HttpRequest buildRequest(String query, Resource resource) throws Exception {
        URI requestUri = new URI(String.format(GIANT_BOMB_SEARCH_REQUEST_FORMAT, this.config.getUrl(), this.config.getKey(), encodeQuery(query), resource.getValue()));
        return HttpRequest.newBuilder().uri(requestUri).build();
    }

    private String encodeQuery(String query) throws Exception {
        return URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
    }

    private List<Object> handleResponse(HttpResponse<String> response, Resource resource) throws JsonProcessingException {
        List<Object> repositoryList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode body = objectMapper.readTree(response.body());

        return mapResponse(body.get("results").iterator(), resource);
    }

    private List<Object> mapResponse(Iterator<JsonNode> iterator, Resource resource) throws IllegalArgumentException {
        List<Object> results = new ArrayList<>();

        while(iterator.hasNext()) {
            JsonNode node = iterator.next();
            Object result = mapResponse(node, resource);
            results.add(result);
        }

        return results;
    }

    private Object mapResponse(JsonNode node, Resource resource) {
        //This would be better implemented using the factory pattern to create a mapper of the right type
        if(resource == Resource.GAME) {
            Game game = new Game();
            game.setName(node.get("name").asText());
            game.setThumbnail(node.get("image").get("thumb_url").asText());
            return game;
        } else {
            throw new IllegalArgumentException("Unsupported resource type: " + resource.getValue());
        }
    }
}
