package es.netmind.mypersonalbankapi;

    import es.netmind.mypersonalbankapi.config.SpringConfig;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.test.context.ContextConfiguration;
    import org.springframework.test.context.junit.jupiter.SpringExtension;

    import javax.persistence.EntityManager;
    import javax.persistence.PersistenceContext;

    import static org.junit.jupiter.api.Assertions.assertNotNull;
    import static org.junit.jupiter.api.Assertions.assertTrue;

    @SpringBootTest
    public class AppTest {

        @PersistenceContext
        EntityManager em;

        @Test
        public void load() {
            assertNotNull(em);
            assertTrue(true);
        }


    }
