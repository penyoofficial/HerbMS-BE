import com.penyo.herbms.service.HerbService;
import com.penyo.herbms.util.SpringConfig;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AspectTest {
  @Test
  public void testGetContext() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
    assertNotNull(ac);
  }

  @Test
  public void testGetEnhancedClass() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
    HerbService ec = ac.getBean(HerbService.class);
    assertNotNull(ec);
  }
}
