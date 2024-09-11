package com.doctor.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AppointmentDAO;
import com.db.DBConnect;

@WebServlet("/updateStatus")
public class UpdateStatus extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			int did = Integer.parseInt(req.getParameter("did"));
			String comment = req.getParameter("comment");
			
			AppointmentDAO dao = new AppointmentDAO(DBConnect.getConn());
			
			HttpSession session = req.getSession();
			
			if(dao.updateCommentStatus(id, did, comment)) {
				session.setAttribute("succMsg", "Comment Updated");
				resp.sendRedirect(req.getContextPath() + "/doctor/patient.jsp");

			} else {
				session.setAttribute("errorMsg", "Something Wrong With Server");
				resp.sendRedirect(req.getContextPath() + "/doctor/patient.jsp");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
