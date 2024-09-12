package com.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DoctorTest {

    @Test
    public void testDoctorGettersAndSetters() {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setId(1);
        doctor.setFullName("Dr. Smith");
        doctor.setDob("1980-01-01");
        doctor.setQualification("MD");
        doctor.setSpecialist("Cardiology");
        doctor.setEmail("dr.smith@example.com");
        doctor.setMobNo("0987654321");
        doctor.setPassword("password123");

        // Act & Assert
        assertEquals(1, doctor.getId());
        assertEquals("Dr. Smith", doctor.getFullName());
        assertEquals("1980-01-01", doctor.getDob());
        assertEquals("MD", doctor.getQualification());
        assertEquals("Cardiology", doctor.getSpecialist());
        assertEquals("dr.smith@example.com", doctor.getEmail());
        assertEquals("0987654321", doctor.getMobNo());
        assertEquals("password123", doctor.getPassword());
    }
}
