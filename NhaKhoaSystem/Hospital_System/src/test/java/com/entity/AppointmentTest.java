package com.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AppointmentTest {

    @Test
    public void testAppointmentGettersAndSetters() {
        // Arrange
        Appointment appointment = new Appointment();
        appointment.setId(1);
        appointment.setUserId(2);
        appointment.setFullName("John Doe");
        appointment.setGender("Male");
        appointment.setAge("30");
        appointment.setAppointmentDate("2024-09-10");
        appointment.setEmail("john.doe@example.com");
        appointment.setPhNo("1234567890");
        appointment.setDiseases("Flu");
        appointment.setDoctorId(3);
        appointment.setAddress("123 Main St");
        appointment.setStatus("Pending");

        // Act & Assert
        assertEquals(1, appointment.getId());
        assertEquals(2, appointment.getUserId());
        assertEquals("John Doe", appointment.getFullName());
        assertEquals("Male", appointment.getGender());
        assertEquals("30", appointment.getAge());
        assertEquals("2024-09-10", appointment.getAppointmentDate());
        assertEquals("john.doe@example.com", appointment.getEmail());
        assertEquals("1234567890", appointment.getPhNo());
        assertEquals("Flu", appointment.getDiseases());
        assertEquals(3, appointment.getDoctorId());
        assertEquals("123 Main St", appointment.getAddress());
        assertEquals("Pending", appointment.getStatus());
    }
}
