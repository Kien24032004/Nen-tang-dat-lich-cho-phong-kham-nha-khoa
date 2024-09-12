package com.doctor.servlet;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import com.dao.DoctorDao;
import com.db.DBConnect;
import com.entity.Doctor;

public class DoctorLoginTest extends Mockito {

    private DoctorLogin servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private DoctorDao doctorDao;

    @BeforeEach
    public void setUp() {
        servlet = new DoctorLogin();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        doctorDao = mock(DoctorDao.class);

        when(request.getSession()).thenReturn(session);
        DBConnect.setConnection(mock(java.sql.Connection.class));  // Mocking DB connection
    }

    @Test
    public void testDoPost_LoginSuccessful() throws ServletException, IOException {
        // Arrange
        String email = "doctor@example.com";
        String password = "password";
        Doctor doctor = new Doctor(1, "Dr. Smith", "1980-01-01", "MD", "Cardiology", email, "1234567890", password);

        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);
        when(doctorDao.login(email, password)).thenReturn(doctor);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(session).setAttribute("doctorObj", doctor);
        verify(response).sendRedirect("doctor/index.jsp");
    }

    @Test
    public void testDoPost_LoginFailed() throws ServletException, IOException {
        // Arrange
        String email = "doctor@example.com";
        String password = "wrongpassword";

        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);
        when(doctorDao.login(email, password)).thenReturn(null);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(session).setAttribute("errorMsg", "Email hoặc password không hợp lệ!!");
        verify(response).sendRedirect("doctor_login.jsp");
    }
}
