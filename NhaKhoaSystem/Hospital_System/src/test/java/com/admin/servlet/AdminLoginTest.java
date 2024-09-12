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

public class AdminLoginTest extends Mockito {

    private AdminLogin servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @BeforeEach
    public void setUp() {
        servlet = new AdminLogin();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testDoPost_LoginSuccess() throws ServletException, IOException {
        // Arrange
        String email = "admin@gmail.com";
        String password = "admin";

        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(session).setAttribute("adminObj", new User());
        verify(response).sendRedirect("admin/index.jsp");
    }

    @Test
    public void testDoPost_LoginFailure() throws ServletException, IOException {
        // Arrange
        String email = "admin@gmail.com";
        String password = "wrongpassword";

        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(session).setAttribute("errorMsg", "Email hoặc password không hợp lệ!!");
        verify(response).sendRedirect("admin_login.jsp");
    }
}
