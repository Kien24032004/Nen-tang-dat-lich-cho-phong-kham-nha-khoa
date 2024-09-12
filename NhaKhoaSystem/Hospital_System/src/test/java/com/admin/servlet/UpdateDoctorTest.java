package com.admin.servlet;

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

import com.dao.DoctorDao;
import com.db.DBConnect;
import com.entity.Doctor;

public class UpdateDoctorTest extends Mockito {

    private UpdateDoctor servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private DoctorDao doctorDao;

    @BeforeEach
    public void setUp() {
        servlet = new UpdateDoctor();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        doctorDao = mock(DoctorDao.class);

        when(request.getSession()).thenReturn(session);
        DBConnect.setConnection(mock(java.sql.Connection.class));  // Mocking DB connection
    }

    @Test
    public void testDoPost_UpdateDoctorSuccess() throws ServletException, IOException {
        // Arrange
        String fullName = "Dr. Jane Doe";
        String dob = "1975-05-10";
        String qualification = "MBBS";
        String spec = "Orthopedics";
        String email = "jane@example.com";
        String mobno = "0987654321";
        String password = "password";
        int id = 2;

        @SuppressWarnings("unused")
		Doctor doctor = new Doctor(id, fullName, dob, qualification, spec, email, mobno, password);

        when(request.getParameter("fullname")).thenReturn(fullName);
        when(request.getParameter("dob")).thenReturn(dob);
        when(request.getParameter("qualification")).thenReturn(qualification);
        when(request.getParameter("spec")).thenReturn(spec);
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("mobno")).thenReturn(mobno);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getParameter("id")).thenReturn(String.valueOf(id));
        when(doctorDao.updateDoctor(any(Doctor.class))).thenReturn(true);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(session).setAttribute("succMsg", "Đã update");
        verify(response).sendRedirect("admin/view_doctor.jsp");
    }

    @Test
    public void testDoPost_UpdateDoctorFailure() throws ServletException, IOException {
        // Arrange
        String fullName = "Dr. Jane Doe";
        String dob = "1975-05-10";
        String qualification = "MBBS";
        String spec = "Orthopedics";
        String email = "jane@example.com";
        String mobno = "0987654321";
        String password = "password";
        int id = 2;

        @SuppressWarnings("unused")
		Doctor doctor = new Doctor(id, fullName, dob, qualification, spec, email, mobno, password);

        when(request.getParameter("fullname")).thenReturn(fullName);
        when(request.getParameter("dob")).thenReturn(dob);
        when(request.getParameter("qualification")).thenReturn(qualification);
        when(request.getParameter("spec")).thenReturn(spec);
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("mobno")).thenReturn(mobno);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getParameter("id")).thenReturn(String.valueOf(id));
        when(doctorDao.updateDoctor(any(Doctor.class))).thenReturn(false);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(session).setAttribute("errorMsg", "something wrong on server");
        verify(response).sendRedirect("admin/view_doctor.jsp");
    }
}
