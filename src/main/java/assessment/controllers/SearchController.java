package assessment.controllers;

import assessment.model.Resource;
import assessment.service.GiantBombService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

@Controller
public class SearchController {

    private GiantBombService service;

    @Autowired
    public SearchController( GiantBombService service) {
        this.service = service;
    }

    @GetMapping("/search/{query}")
    @ResponseBody
    public List<Object> doSearch(@PathVariable String query) {
        try {
            List<Object> results = this.service.doSearch(query, Resource.GAME);
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
