package tn.esprit.spring.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.Niveau;

import static org.junit.jupiter.api.Assertions.*;

class NiveauTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testEnumSerialization() throws Exception {
        // Test serialization to JSON
        String juniorJson = objectMapper.writeValueAsString(Niveau.JUNIOR);
        assertEquals("\"Junior\"", juniorJson);

        String seniorJson = objectMapper.writeValueAsString(Niveau.SENIOR);
        assertEquals("\"SENIOR\"", seniorJson);

        String expertJson = objectMapper.writeValueAsString(Niveau.EXPERT);
        assertEquals("\"Expert\"", expertJson);
    }

    // The existing tests
    @Test
    void testGetValue() {
        assertEquals("Junior", Niveau.JUNIOR.getValue());
        assertEquals("SENIOR", Niveau.SENIOR.getValue());
        assertEquals("Expert", Niveau.EXPERT.getValue());
    }

    @Test
    void testEnumValues() {
        Niveau[] values = Niveau.values();
        assertEquals(3, values.length);
        assertEquals(Niveau.JUNIOR, values[0]);
        assertEquals(Niveau.SENIOR, values[1]);
        assertEquals(Niveau.EXPERT, values[2]);
    }
}

