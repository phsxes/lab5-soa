package soa.web;

import java.util.Map;
import java.util.HashMap;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SearchController {

  private final ProducerTemplate producerTemplate;

  @Autowired
  public SearchController(ProducerTemplate producerTemplate) {
    this.producerTemplate = producerTemplate;
  }

  @RequestMapping("/")
  public String index() {
    return "index";
  }


  @RequestMapping(value = "/search")
  @ResponseBody
  public Object search(@RequestParam("q") String q,
                       @RequestParam(name = "MaxTweets", defaultValue = "10", required = false) int MaxTweets) {

    // A hashmap is created to store both the tweet headers and the max count headers
    Map<String, Object> headers = new HashMap<>();
    headers.put("CamelTwitterKeywords", q);
    headers.put("CamelTwitterCount", MaxTweets);

    return producerTemplate.requestBodyAndHeader("direct:search", "", headers);
  }
}
