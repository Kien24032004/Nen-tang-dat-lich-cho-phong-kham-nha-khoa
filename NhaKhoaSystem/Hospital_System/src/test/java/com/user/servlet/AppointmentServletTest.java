package com.user.servlet;

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

import com.dao.AppointmentDAO;
import com.db.DBConnect;
import com.entity.Appointment;

public class AppointmentServletTest extends Mockito {

    private AppointmentServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private AppointmentDAO appointmentDAO;

    @BeforeEach
    public void setUp() {
        servlet = new AppointmentServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        appointmentDAO = mock(AppointmentDAO.class);

        when(request.getSession()).thenReturn(session);
        DBConnect.setConnection(mock(java.sql.Connection.class));  // Mocking DB connection
    }

    @Test
    public void testDoPost_AppointmentSuccess() throws ServletException, IOException {
        // Arrange
        int userId = 1;
        String fullname = "John Doe";
        String gender = "Male";
        String age = "30";
        String appointmentDate = "2024-09-11";
        String email = "john@example.com";
        String phno = "1234567890";
        String diseases = "Flu";
        int doctorId = 1;
        String address = "123 Street";

        Appointment appointment = new Appointment(userId, fullname, gender, age, appointmentDate, email, phno, diseases, doctorId, address, "Pending");

        when(request.getParameter("userid")).thenReturn(String.valueOf(userId));
        when(request.getParameter("fullname")).thenReturn(fullname);
        when(request.getParameter("gender")).thenReturn(gender);
        when(request.getParameter("age")).thenReturn(age);
        when(request.getParameter("appointment_date")).thenReturn(appointmentDate);
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("phno")).thenReturn(phno);
        when(request.getParameter("diseases")).thenReturn(diseases);
        when(request.getParameter("doctor")).thenReturn(String.valueOf(doctorId));
        when(request.getParameter("address")).thenReturn(address);
        when(appointmentDAO.addAppointment(any(Appointment.class))).thenReturn(true);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(session).setAttribute("succMsg", "Appointment Successfully");
        verify(response).sendRedirect("user_appointment.jsp");
    }

    @Test
    public void testDoPost_AppointmentFailure() throws ServletException, IOException {
        // Arrange
        int userId = 1;
        String fullname = "John Doe";
        String gender = "Male";
        String age = "30";
        String appointmentDate = "2024-09-11";
        String email = "john@example.com";
        String phno = "1234567890";
        String diseases = "Flu";
        int doctorId = 1;
        String address = "123 Street";

        @SuppressWarnings("unused")
		Appointment appointment = new Appointment(userId, fullname, gender, age, appointmentDate, email, phno, diseases, doctorId, address, "Pending");

        when(request.getParameter("userid")).thenReturn(String.valueOf(userId));
        when(request.getParameter("fullname")).thenReturn(fullname);
        when(request.getParameter("gender")).thenReturn(gender);
        when(request.getParameter("age")).thenReturn(age);
        when(request.getParameter("appointment_date")).thenReturn(appointmentDate);
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("phno")).thenReturn(phno);
        when(request.getParameter("diseases")).thenReturn(diseases);
        when(request.getParameter("doctor")).thenReturn(String.valueOf(doctorId));
        when(request.getParameter("address")).thenReturn(address);
        when(appointmentDAO.addAppointment(any(Appointment.class))).thenReturn(false);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(session).setAttribute("errorMsg", "Something wrong on server");
        verify(response).sendRedirect("user_appointment.jsp");
    }
}
