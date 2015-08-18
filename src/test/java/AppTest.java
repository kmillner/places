import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  //test content comes from layout.vtl
  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("places");
  }

  @Test
  public void cityIsCreatedTest() {
    goTo("http://localhost:4567/");
    fill("#nameOfCity").with("Portland");
    submit(".btn");
    assertThat(pageSource()).contains("Your city has been saved.");
  }
  @Test
  public void cityIsDisplayedTest() {
    goTo("http://localhost:4567/");
    fill("#nameOfCity").with("Portland"); //finds form: nameOfCity in index.vtl
    submit(".btn");
    click("a", withText("Go Back"));
    assertThat(pageSource()).contains("Portland");
  }
  @Test
  public void multipleCitiesAreDisplayedTest() {
    goTo("http://localhost:4567/");
    fill("#nameOfCity").with("Portland");
    submit(".btn");
    click("a", withText("Go Back"));
    fill("#nameOfCity").with("Atlanta");
    submit(".btn");
    click("a", withText("Go Back"));
    assertThat(pageSource()).contains("Portland");
    assertThat(pageSource()).contains("Atlanta");
  }
}
