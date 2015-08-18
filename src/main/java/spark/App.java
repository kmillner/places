import java.util.HashMap;
import java.util.ArrayList;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";


    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      model.put("cities", request.session().attribute("cities"));
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/cities", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      ArrayList<City> cities = request.session().attribute("cities");

      if (cities == null) {
        cities = new ArrayList<City>();
        request.session().attribute("cities", cities);
      }

      String nameOfCity = request.queryParams("nameOfCity");
      City newCity = new City(nameOfCity);
      request.session().attribute("nameOfCity", nameOfCity);

      cities.add(newCity);

      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
 }
