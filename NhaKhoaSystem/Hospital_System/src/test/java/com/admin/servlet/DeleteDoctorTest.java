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

public class DeleteDoctorTest extends Mockito {

    private DeleteDoctor servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private DoctorDao doctorDao;

    @BeforeEach
    public void setUp() {
        servlet = new DeleteDoctor();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        doctorDao = mock(DoctorDao.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testDoGet_DeleteDoctorSuccess() throws ServletException, IOException {
        // Arrange
        int id = 1;

        when(request.getParameter("id")).thenReturn(String.valueOf(id));
        when(doctorDao.deleteDoctor(id)).thenReturn(true);

        // Act
        servlet.doGet(request, response);

        // Assert
        verify(session).setAttribute("succMsg", "Đã xóa bác sĩ");
        verify(response).sendRedirect("admin/view_doctor.jsp");
    }

    @Test
    public void testDoGet_DeleteDoctorFailure() throws ServletException, IOException {
        // Arrange
        int id = 1;

        when(request.getParameter("id")).thenReturn(String.valueOf(id));
        when(doctorDao.deleteDoctor(id)).thenReturn(false);

        // Act
        servlet.doGet(request, response);

        // Assert
        verify(session).setAttribute("errorMsg", "something wrong on server");
        verify(response).sendRedirect("admin/view_doctor.jsp");
    }
}
