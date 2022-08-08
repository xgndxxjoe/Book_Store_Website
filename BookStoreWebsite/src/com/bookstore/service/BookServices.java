package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;

public class BookServices {
	private EntityManager entityManager;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private BookDAO bookDAO;
	
	public BookServices(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {
		super();
		this.entityManager = entityManager;
		this.request = request;
		this.response = response;
		bookDAO = new BookDAO(entityManager);
	}

	public void listBooks() throws ServletException, IOException {
		List<Book> listBook = bookDAO.listAll();
		
		request.setAttribute("listBook", listBook);
		
		String bookPage = "book_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(bookPage);
		dispatcher.forward(request, response);
	}
}
