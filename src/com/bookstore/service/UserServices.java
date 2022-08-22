package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class UserServices {
	private EntityManager entityManager;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private UserDAO userDAO;

	public UserServices(EntityManager entityManager,
			HttpServletRequest request, HttpServletResponse response) {
		this.entityManager = entityManager;
		this.request = request;
		this.response = response;
		
		userDAO = new UserDAO(entityManager);
	}

	public void listUser() throws ServletException, IOException {
		listUser(null);
	}

	public void listUser(String message) throws ServletException, IOException {
		List<Users> listUsers = userDAO.listAll();

		request.setAttribute("listUsers", listUsers);
		if (message != null) {
			request.setAttribute("message", message);
		}

		String listPage = "user_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);

		requestDispatcher.forward(request, response);

	}

	public void createUser() throws ServletException, IOException {
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");

		Users existUser = userDAO.findByEmail(email);

		if (existUser != null) {
			String message = "Could not create user. A user with email " + email + " already exists";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		} else {
			Users newUser = new Users(email, fullname, password);
			userDAO.create(newUser);
			listUser("New user created successfully");
		}
	}

	public void editUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		Users user = userDAO.get(userId);

		// Kiem tra user co ton tai hay khong
		if (user == null) {
			String errorMessage = "Could not find user with ID " + userId + ".";
			request.setAttribute("message", errorMessage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		}

		String editPage = "user_form.jsp";
		request.setAttribute("user", user);

		RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);
	}

	public void updateUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");

		Users userById = userDAO.get(userId);
		Users userByEmail = userDAO.findByEmail(email);

		// so sanh email update voi email duoc tim thay trong danh sach user
		if (userByEmail != null && userByEmail.getUserId() != userById.getUserId()) {
			String message = "Could not update user. Because user with email: " + email + " already existes.";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		} else {
			Users user = new Users(userId, email, fullName, password);
			userDAO.update(user);

			String message = "User has been updated successfully!";
			listUser(message);
		}

	}

	public void deleteUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		Users user = userDAO.get(userId);
		String message = "User has been deleted successfully!";

		if (user == null) {
			String errorMessage = "Could not find user with ID" + userId + " .";
			request.setAttribute("message", errorMessage);

			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		}

		if (userId == 1) {
			message = "The default admin user account cannot be deleted.";

			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
			return;
		}

		userDAO.delete(userId);
		listUser(message);
	}

	public void login() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		boolean checkResult = userDAO.checkLogin(email, password);
		
		if(checkResult) {
			
			request.getSession().setAttribute("useremail", email);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
			dispatcher.forward(request, response);
		} else {
			String message = "Login failed!";
			
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		
	}
}
