package com.javabotanist.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javabotanist.dao.UserDAO;
import com.javabotanist.entities.User;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		HttpSession session = request.getSession();
		session.setAttribute("firstName", firstName);
		session.setAttribute("lastName", lastName);
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		new UserDAO().createUser(user);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
		dispatcher.forward(request, response);
	}

}
