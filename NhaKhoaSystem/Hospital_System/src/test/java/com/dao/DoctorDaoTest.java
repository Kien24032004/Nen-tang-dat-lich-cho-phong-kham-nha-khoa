package com.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.entity.Doctor;

public class DoctorDaoTest {

    private DoctorDao doctorDao;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() throws Exception {
        // Create mock objects
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);

        // Set up DoctorDao with mocked connection
        doctorDao = new DoctorDao(mockConnection);
    }

    @Test
    public void testRegisterDoctor() throws Exception {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setFullName("Dr. Smith");
        doctor.setDob("1980-01-01");
        doctor.setQualification("MD");
        doctor.setSpecialist("Cardiologist");
        doctor.setEmail("dr.smith@example.com");
        doctor.setMobNo("9876543210");
        doctor.setPassword("password123");

        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        boolean result = doctorDao.registerDoctor(doctor);

        // Assert
        assertTrue(result);
        Mockito.verify(mockPreparedStatement).setString(1, "Dr. Smith");
        Mockito.verify(mockPreparedStatement).setString(2, "1980-01-01");
        Mockito.verify(mockPreparedStatement).setString(3, "MD");
        Mockito.verify(mockPreparedStatement).setString(4, "Cardiologist");
        Mockito.verify(mockPreparedStatement).setString(5, "dr.smith@example.com");
        Mockito.verify(mockPreparedStatement).setString(6, "9876543210");
        Mockito.verify(mockPreparedStatement).setString(7, "password123");
    }

    @Test
    public void testGetAllDoctor() throws Exception {
        // Arrange
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(mockResultSet.getInt(1)).thenReturn(1);
        Mockito.when(mockResultSet.getString(2)).thenReturn("Dr. Smith");
        Mockito.when(mockResultSet.getString(3)).thenReturn("1980-01-01");
        Mockito.when(mockResultSet.getString(4)).thenReturn("MD");
        Mockito.when(mockResultSet.getString(5)).thenReturn("Cardiologist");
        Mockito.when(mockResultSet.getString(6)).thenReturn("dr.smith@example.com");
        Mockito.when(mockResultSet.getString(7)).thenReturn("9876543210");
        Mockito.when(mockResultSet.getString(8)).thenReturn("password123");

        // Act
        List<Doctor> doctors = doctorDao.getAllDoctor();

        // Assert
        assertEquals(1, doctors.size());
        Doctor doctor = doctors.get(0);
        assertEquals("Dr. Smith", doctor.getFullName());
        assertEquals("1980-01-01", doctor.getDob());
        assertEquals("MD", doctor.getQualification());
        assertEquals("Cardiologist", doctor.getSpecialist());
        assertEquals("dr.smith@example.com", doctor.getEmail());
        assertEquals("9876543210", doctor.getMobNo());
        assertEquals("password123", doctor.getPassword());
    }

    @Test
    public void testGetDoctorById() throws Exception {
        // Arrange
        int id = 1;

        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(mockResultSet.getInt(1)).thenReturn(1);
        Mockito.when(mockResultSet.getString(2)).thenReturn("Dr. Smith");
        Mockito.when(mockResultSet.getString(3)).thenReturn("1980-01-01");
        Mockito.when(mockResultSet.getString(4)).thenReturn("MD");
        Mockito.when(mockResultSet.getString(5)).thenReturn("Cardiologist");
        Mockito.when(mockResultSet.getString(6)).thenReturn("dr.smith@example.com");
        Mockito.when(mockResultSet.getString(7)).thenReturn("9876543210");
        Mockito.when(mockResultSet.getString(8)).thenReturn("password123");

        // Act
        Doctor doctor = doctorDao.getDoctorById(id);

        // Assert
        assertEquals("Dr. Smith", doctor.getFullName());
        assertEquals("1980-01-01", doctor.getDob());
        assertEquals("MD", doctor.getQualification());
        assertEquals("Cardiologist", doctor.getSpecialist());
        assertEquals("dr.smith@example.com", doctor.getEmail());
        assertEquals("9876543210", doctor.getMobNo());
        assertEquals("password123", doctor.getPassword());
    }

    @Test
    public void testUpdateDoctor() throws Exception {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setId(1);
        doctor.setFullName("Dr. Smith");
        doctor.setDob("1980-01-01");
        doctor.setQualification("MD");
        doctor.setSpecialist("Cardiologist");
        doctor.setEmail("dr.smith@example.com");
        doctor.setMobNo("9876543210");
        doctor.setPassword("newpassword123");

        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        boolean result = doctorDao.updateDoctor(doctor);

        // Assert
        assertTrue(result);
        Mockito.verify(mockPreparedStatement).setString(1, "Dr. Smith");
        Mockito.verify(mockPreparedStatement).setString(2, "1980-01-01");
        Mockito.verify(mockPreparedStatement).setString(3, "MD");
        Mockito.verify(mockPreparedStatement).setString(4, "Cardiologist");
        Mockito.verify(mockPreparedStatement).setString(5, "dr.smith@example.com");
        Mockito.verify(mockPreparedStatement).setString(6, "9876543210");
        Mockito.verify(mockPreparedStatement).setString(7, "newpassword123");
        Mockito.verify(mockPreparedStatement).setInt(8, 1);
    }

    @Test
    public void testDeleteDoctor() throws Exception {
        // Arrange
        int id = 1;

        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        boolean result = doctorDao.deleteDoctor(id);

        // Assert
        assertTrue(result);
        Mockito.verify(mockPreparedStatement).setInt(1, id);
    }

    @Test
    public void testLogin() throws Exception {
        // Arrange
        String email = "dr.smith@example.com";
        String password = "password123";

        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(mockResultSet.getInt(1)).thenReturn(1);
        Mockito.when(mockResultSet.getString(2)).thenReturn("Dr. Smith");
        Mockito.when(mockResultSet.getString(3)).thenReturn("1980-01-01");
        Mockito.when(mockResultSet.getString(4)).thenReturn("MD");
        Mockito.when(mockResultSet.getString(5)).thenReturn("Cardiologist");
        Mockito.when(mockResultSet.getString(6)).thenReturn("dr.smith@example.com");
        Mockito.when(mockResultSet.getString(7)).thenReturn("9876543210");
        Mockito.when(mockResultSet.getString(8)).thenReturn("password123");

        // Act
        Doctor doctor = doctorDao.login(email, password);

        // Assert
        assertEquals("Dr. Smith", doctor.getFullName());
        assertEquals("1980-01-01", doctor.getDob());
        assertEquals("MD", doctor.getQualification());
        assertEquals("Cardiologist", doctor.getSpecialist());
        assertEquals("dr.smith@example.com", doctor.getEmail());
        assertEquals("9876543210", doctor.getMobNo());
        assertEquals("password123", doctor.getPassword());
    }
}
