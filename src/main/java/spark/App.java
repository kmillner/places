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
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/Tasks", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String description = request.queryParams("description");
      Task newTask = new Task(description);
      request.session().attribute("task");

      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  {
    post("/task", (request, response) -> {
  HashMap<String, Object> model = new HashMap<String, Object>();

  ArrayList<Task> task = request.session().attribute("Task");

if (task == null) {
  task = new ArrayList<Task>();
  request.session().attribute("task", task);
}

  String description = request.queryParams("description");
  Task newTask = new Task(description);

  task.add(newTask);

  model.put("template", "templates/success.vtl");

  return new ModelAndView(model, layout);
 }, new VelocityTemplateEngine());
 }
 }
 }
