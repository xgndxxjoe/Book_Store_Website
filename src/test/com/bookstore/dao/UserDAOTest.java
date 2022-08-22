package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.swing.plaf.ListUI;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest extends BaseDAOTest{
	private static UserDAO userDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		BaseDAOTest.setUpBeforeClass();
		userDAO = new UserDAO(entityManager);
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception{
		BaseDAOTest.tearDownAfterClass();
	}
	
	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("tommyshlby@gmail.com");
		user1.setFullname("Tommy Johnson");
		user1.setPassword("PeakyBlinder");
		
		user1 = userDAO.create(user1);
		
		assertTrue(user1.getUserId() > 0);
	}
	
	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user1 = new Users();
		user1 = userDAO.create(user1);
	}
	
	@Test
	public void testUpdateUsers() {
		Users user = new Users();
		user.setUserId(4);
		user.setEmail("nam@codejava.com");
		user.setFullname("Nam Ha Minh");
		user.setPassword("mysecret");
		
		user = userDAO.update(user);
		String expected = "mysecret";
		String actual = user.getPassword();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetUsersFound() {
		Integer userId = 1;
		Users user = userDAO.get(userId);
		
		if(user != null) {
			System.out.println(user.getEmail());
		}
		
		assertNotNull(user);
	}
	
	@Test
	public void testGetUserNotFound() {
		Integer userId = 99;
		Users user = userDAO.get(userId);
		
		assertNull(user);
	}
	
	@Test
	public void testDeleteUsers() {
		Integer userId = 12;
		userDAO.delete(userId);
		
		Users user = userDAO.get(userId);
		
		assertNull(user);
	}
	
	@Test(expected = Exception.class)
	public void testDeleteNotExistUser() {
		Integer userId = 5;
		userDAO.delete(userId);
	}
	
	@Test
	public void testListAll() {
		List<Users> listUser = userDAO.listAll();
		
		for(Users user : listUser) {
			System.out.println(user.getFullname());
		}
		
		assertTrue(listUser.size() > 0);
	}
	
	@Test
	public void testCountAll() {
		long totalUsers = userDAO.count();
		long size = userDAO.listAll().size();
		
		System.out.println(totalUsers);
		
		assertTrue(totalUsers == size);
	}
	
	@Test
	public void testFindByEmail() {
		String email = "bookstore@gmail.com";
		Users user = userDAO.findByEmail(email);
		
		assertNotNull(user);
	}
	
	@Test
	public void testCheckLogin() {
		String email = "joedpham26@gmail.com";
		String password = "helloworld";
		boolean result = userDAO.checkLogin(email, password);
		
		assertTrue(result);
	}
}
