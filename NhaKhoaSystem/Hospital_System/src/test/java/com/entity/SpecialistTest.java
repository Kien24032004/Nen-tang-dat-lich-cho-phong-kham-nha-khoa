package com.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SpecialistTest {

    @Test
    public void testSpecialistGettersAndSetters() {
        // Arrange
        Specialist specialist = new Specialist();
        specialist.setId(1);
        specialist.setSpecialistName("Orthopedics");

        // Act & Assert
        assertEquals(1, specialist.getId());
        assertEquals("Orthopedics", specialist.getSpecialistName());
    }
}
