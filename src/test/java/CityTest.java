import org.junit.*;
import static org.junit.Assert.*;

public class CityTest {

  @Test
  public void City_instantiatesCorrectly_true() {
    City newCity = new City("Portland");
    assertEquals(true, newCity instanceof City);
  }
  @Test
  public void City_instantiatesNameOfCity_true() {
    City newCity = new City("Portland");
    assertEquals("Portland", newCity.getnewCity());
  }
}
