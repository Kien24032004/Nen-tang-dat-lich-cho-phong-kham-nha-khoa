package com.db;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DBConnectTest extends Mockito {

    private Connection mockConnection;

    @BeforeEach
    public void setUp() {
        // Reset static connection in DBConnect class
        try {
            // Use reflection to reset the static field if needed
            java.lang.reflect.Field field = DBConnect.class.getDeclaredField("conn");
            field.setAccessible(true);
            field.set(null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mockConnection = mock(Connection.class);
        DBConnect.setConnection(mockConnection);
    }

    @Test
    public void testGetConnReturnsMockConnection() {
        // Act
        Connection conn = DBConnect.getConn();

        // Assert
        assertNotNull(conn);
        assertEquals(mockConnection, conn);
    }

    @SuppressWarnings("static-access")
	@Test
    public void testGetConnCallsDriverManager() throws Exception {
        // Arrange
        Connection mockConnection = mock(Connection.class);
        DriverManager driverManager = mock(DriverManager.class);
        when(driverManager.getConnection(any(String.class), any(String.class), any(String.class)))
                .thenReturn(mockConnection);

        // Act
        @SuppressWarnings("unused")
		Connection conn = DBConnect.getConn();

        // Assert
        verify(driverManager).getConnection("jdbc:mysql://localhost:3306/hospital", "root", "24032004");
    }

    @Test
    public void testGetConnHandlesException() throws Exception {
        // Arrange
        DBConnect.setConnection(null);

        // Mock static methods
        mockStatic(DriverManager.class);
        when(DriverManager.getConnection(any(String.class), any(String.class), any(String.class)))
                .thenThrow(new RuntimeException("Connection failed"));

        // Act
        Connection conn = DBConnect.getConn();

        // Assert
        assertNull(conn); // Ensure connection is null if exception occurs
    }

	private void mockStatic(Class<DriverManager> class1) {
		// TODO Auto-generated method stub
		
	}
}
