package com.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.entity.Specialist;

public class SpecialistDaoTest {

    private SpecialistDao specialistDao;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() throws Exception {
        // Create mock objects
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);

        // Set up SpecialistDao with mocked connection
        specialistDao = new SpecialistDao(mockConnection);
    }

    @Test
    public void testAddSpecialist() throws Exception {
        // Arrange
        String specialistName = "Cardiologist";

        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        boolean result = specialistDao.addSpecialist(specialistName);

        // Assert
        assertTrue(result);
        Mockito.verify(mockPreparedStatement).setString(1, specialistName);
    }

    @Test
    public void testGetAllSpecialist() throws Exception {
        // Arrange
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(mockResultSet.getInt(1)).thenReturn(1);
        Mockito.when(mockResultSet.getString(2)).thenReturn("Cardiologist");

        // Act
        List<Specialist> specialists = specialistDao.getAllSpecialist();

        // Assert
        assertEquals(1, specialists.size());
        Specialist specialist = specialists.get(0);
        assertEquals("Cardiologist", specialist.getSpecialistName());
    }
}
