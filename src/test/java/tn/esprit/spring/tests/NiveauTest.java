package tn.esprit.spring.tests;

import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.Niveau;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NiveauTest {

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
