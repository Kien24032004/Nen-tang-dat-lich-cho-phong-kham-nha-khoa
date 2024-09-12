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

import com.dao.SpecialistDao;

public class AddSpecialistTest extends Mockito {

    private AddSpecialist servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private SpecialistDao specialistDao;

    @BeforeEach
    public void setUp() {
        servlet = new AddSpecialist();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        specialistDao = mock(SpecialistDao.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testDoPost_AddSpecialistSuccess() throws ServletException, IOException {
        // Arrange
        String specName = "Neurology";

        when(request.getParameter("specName")).thenReturn(specName);
        when(specialistDao.addSpecialist(specName)).thenReturn(true);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(session).setAttribute("succMsg", "Đã thêm Specialist");
        verify(response).sendRedirect("admin/index.jsp");
    }

    @Test
    public void testDoPost_AddSpecialistFailure() throws ServletException, IOException {
        // Arrange
        String specName = "Neurology";

        when(request.getParameter("specName")).thenReturn(specName);
        when(specialistDao.addSpecialist(specName)).thenReturn(false);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(session).setAttribute("errorMsg", "Something wrong on server");
        verify(response).sendRedirect("admin/index.jsp");
    }
}
