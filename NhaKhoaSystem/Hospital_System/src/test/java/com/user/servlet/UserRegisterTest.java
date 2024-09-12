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

import com.dao.UserDao;
import com.db.DBConnect;
import com.entity.User;

public class UserRegisterTest extends Mockito {

    private UserRegister servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private UserDao userDao;

    @BeforeEach
    public void setUp() {
        servlet = new UserRegister();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        userDao = mock(UserDao.class);

        when(request.getSession()).thenReturn(session);
        DBConnect.setConnection(mock(java.sql.Connection.class));  // Mocking DB connection
    }

    @Test
    public void testDoPost_RegisterSuccess() throws ServletException, IOException {
        // Arrange
        String fullName = "John Doe";
        String email = "john@example.com";
        String password = "password";
        @SuppressWarnings("unused")
		User user = new User(fullName, email, password);

        when(request.getParameter("fullname")).thenReturn(fullName);
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);
        when(userDao.register(any(User.class))).thenReturn(true);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(session).setAttribute("sucMsg", "Register Successfully");
        verify(response).sendRedirect("signup.jsp");
    }

    @Test
    public void testDoPost_RegisterFailure() throws ServletException, IOException {
        // Arrange
        String fullName = "John Doe";
        String email = "john@example.com";
        String password = "password";

        when(request.getParameter("fullname")).thenReturn(fullName);
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);
        when(userDao.register(any(User.class))).thenReturn(false);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(session).setAttribute("errorMsg", "Somthing wrong on server");
        verify(response).sendRedirect("signup.jsp");
    }
}
