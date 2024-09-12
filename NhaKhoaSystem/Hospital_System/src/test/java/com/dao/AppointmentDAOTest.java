package com.dao;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.entity.Appointment;

public class AppointmentDAOTest extends Mockito {

    private AppointmentDAO appointmentDAO;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @BeforeEach
    public void setUp() throws Exception {
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);

        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        appointmentDAO = new AppointmentDAO(connection);
    }

    @Test
    public void testAddAppointmentSuccess() throws Exception {
        // Arrange
        Appointment appointment = new Appointment(1, "John Doe", "Male", "30", "2024-10-10", "john@example.com", "123456789", "Flu", 101, "123 Main St", "Pending");
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Act
        boolean result = appointmentDAO.addAppointment(appointment);

        // Assert
        assertTrue(result);
        verify(preparedStatement).setInt(1, appointment.getUserId());
        verify(preparedStatement).setString(2, appointment.getFullName());
        // Verify other set methods
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testAddAppointmentFailure() throws Exception {
        // Arrange
        Appointment appointment = new Appointment(1, "John Doe", "Male", "30", "2024-10-10", "john@example.com", "123456789", "Flu", 101, "123 Main St", "Pending");
        when(preparedStatement.executeUpdate()).thenReturn(0);

        // Act
        boolean result = appointmentDAO.addAppointment(appointment);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testGetAllAppointmentsByLoginUser() throws Exception {
        // Arrange
        int userId = 1;
        Appointment appointment = new Appointment(1, userId, "John Doe", "Male", "30", "2024-10-10", "john@example.com", "123456789", "Flu", 101, "123 Main St", "Pending");
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);

        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(appointment.getId());
        when(resultSet.getInt(2)).thenReturn(appointment.getUserId());
        when(resultSet.getString(3)).thenReturn(appointment.getFullName());
        // Set other fields similarly

        // Act
        List<Appointment> result = appointmentDAO.getAllAppointmentsByLoginUser(userId);

        // Assert
        assertEquals(appointments, result);
        verify(preparedStatement).setInt(1, userId);
        verify(resultSet, times(2)).next();
    }

    @Test
    public void testGetAppointmentById() throws Exception {
        // Arrange
        int id = 1;
        Appointment appointment = new Appointment(id, 1, "John Doe", "Male", "30", "2024-10-10", "john@example.com", "123456789", "Flu", 101, "123 Main St", "Pending");

        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(appointment.getId());
        // Set other fields similarly

        // Act
        Appointment result = appointmentDAO.getAppointmentById(id);

        // Assert
        assertEquals(appointment, result);
        verify(preparedStatement).setInt(1, id);
        verify(resultSet, times(2)).next();
    }

    @Test
    public void testGetAllAppointmentByDoctorLogin() throws Exception {
        // Arrange
        int doctorId = 101;
        Appointment appointment = new Appointment(1, 1, "John Doe", "Male", "30", "2024-10-10", "john@example.com", "123456789", "Flu", doctorId, "123 Main St", "Pending");
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);

        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(appointment.getId());
        // Set other fields similarly

        // Act
        List<Appointment> result = appointmentDAO.getAllAppointmentByDoctorLogin(doctorId);

        // Assert
        assertEquals(appointments, result);
        verify(preparedStatement).setInt(1, doctorId);
        verify(resultSet, times(2)).next();
    }

    @Test
    public void testUpdateCommentStatusSuccess() throws Exception {
        // Arrange
        int id = 1;
        int doctorId = 101;
        String comment = "Updated";

        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Act
        boolean result = appointmentDAO.updateCommentStatus(id, doctorId, comment);

        // Assert
        assertTrue(result);
        verify(preparedStatement).setString(1, comment);
        verify(preparedStatement).setInt(2, id);
        verify(preparedStatement).setInt(3, doctorId);
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testUpdateCommentStatusFailure() throws Exception {
        // Arrange
        int id = 1;
        int doctorId = 101;
        String comment = "Updated";

        when(preparedStatement.executeUpdate()).thenReturn(0);

        // Act
        boolean result = appointmentDAO.updateCommentStatus(id, doctorId, comment);

        // Assert
        assertFalse(result);
    }
}
