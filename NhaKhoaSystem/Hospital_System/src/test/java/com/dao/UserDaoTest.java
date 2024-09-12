package com.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.entity.User;

public class UserDaoTest {

    private UserDao userDao;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() throws Exception {
        // Create mock objects
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);

        // Set up UserDao with mocked connection
        userDao = new UserDao(mockConnection);
    }

    @Test
    public void testRegister() throws Exception {
        // Arrange
        User user = new User();
        user.setFullName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");

        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        boolean result = userDao.register(user);

        // Assert
        assertTrue(result);
        Mockito.verify(mockPreparedStatement).setString(1, "John Doe");
        Mockito.verify(mockPreparedStatement).setString(2, "john.doe@example.com");
        Mockito.verify(mockPreparedStatement).setString(3, "password123");
    }

    @Test
    public void testLogin() throws Exception {
        // Arrange
        String email = "john.doe@example.com";
        String password = "password123";

        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(mockResultSet.getInt(1)).thenReturn(1);
        Mockito.when(mockResultSet.getString(2)).thenReturn("John Doe");
        Mockito.when(mockResultSet.getString(3)).thenReturn("john.doe@example.com");
        Mockito.when(mockResultSet.getString(4)).thenReturn("password123");

        // Act
        User user = userDao.login(email, password);

        // Assert
        assertEquals(1, user.getId());
        assertEquals("John Doe", user.getFullName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
    }
}
