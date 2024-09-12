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

import com.dao.AppointmentDAO;
import com.db.DBConnect;

public class UpdateStatusTest extends Mockito {

    private UpdateStatus servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private AppointmentDAO appointmentDAO;

    @BeforeEach
    public void setUp() {
        servlet = new UpdateStatus();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        appointmentDAO = mock(AppointmentDAO.class);

        when(request.getSession()).thenReturn(session);
        DBConnect.setConnection(mock(java.sql.Connection.class));  // Mocking DB connection
    }

    @Test
    public void testDoPost_UpdateSuccessful() throws ServletException, IOException {
        // Arrange
        int id = 1;
        int did = 1;
        String comment = "Updated comment";

        when(request.getParameter("id")).thenReturn(String.valueOf(id));
        when(request.getParameter("did")).thenReturn(String.valueOf(did));
        when(request.getParameter("comment")).thenReturn(comment);
        when(appointmentDAO.updateCommentStatus(id, did, comment)).thenReturn(true);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(session).setAttribute("succMsg", "Comment Updated");
        verify(response).sendRedirect("doctor/patient.jsp");
    }

    @Test
    public void testDoPost_UpdateFailed() throws ServletException, IOException {
        // Arrange
        int id = 1;
        int did = 1;
        String comment = "Updated comment";

        when(request.getParameter("id")).thenReturn(String.valueOf(id));
        when(request.getParameter("did")).thenReturn(String.valueOf(did));
        when(request.getParameter("comment")).thenReturn(comment);
        when(appointmentDAO.updateCommentStatus(id, did, comment)).thenReturn(false);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(session).setAttribute("errorMsg", "Something Wrong With Server");
        verify(response).sendRedirect("doctor/patient.jsp");
    }
}
