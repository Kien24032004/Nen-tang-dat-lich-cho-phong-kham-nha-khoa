package com.doctor.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DoctorLogoutTest extends Mockito {

    private DoctorLogout servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @BeforeEach
    public void setUp() {
        servlet = new DoctorLogout();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testDoGet() throws ServletException, IOException {
        // Act
        servlet.doGet(request, response);

        // Assert
        verify(session).removeAttribute("doctorObj");
        verify(session).setAttribute("succMsg", "Doctor logout successfully");
        verify(response).sendRedirect("doctor_login.jsp");
    }
}
