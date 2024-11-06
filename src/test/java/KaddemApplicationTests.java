import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tn.esprit.spring.kaddem.KaddemApplication;

@SpringBootTest(classes = KaddemApplication.class)
class KaddemApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        assert(applicationContext != null);
    }
}
