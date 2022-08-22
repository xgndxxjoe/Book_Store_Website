package com.bookstore.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest extends BaseDAOTest {
	private static BookDAO bookDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		bookDao = new BookDAO(entityManager);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

	@Test
	public void testUpdateBook() throws ParseException, IOException {
		Book newBook = new Book();
		newBook.setBookId(1);
		
		Category category = new Category("Java Basic");
		category.setCategoryId(1);
		newBook.setCategory(category);
		
		newBook.setTitle("Effective Java (2nd Edition)");
		newBook.setAuthor("Joshua Bloch");
		newBook.setDescription("Are you looking for a deeper understanding of the Java™ programming language so that you can write code that is clearer, more correct, more robust, and more reusable? Look no further! Effective Java™, Second Edition, brings together seventy-eight indispensable programmer’s rules of thumb: working, best-practice solutions for the programming challenges you encounter every day.\r\n" + 
				"This highly anticipated new edition of the classic, Jolt Award-winning work has been thoroughly updated to cover Java SE 5 and Java SE 6 features introduced since the first edition. Bloch explores new design patterns and language idioms, showing you how to make the most of features ranging from generics to enums, annotations to autoboxing.\r\n" + 
				"Each chapter in the book consists of several “items” presented in the form of a short, standalone essay that provides specific advice, insight into Java platform subtleties, and outstanding code examples. The comprehensive descriptions and explanations for each item illuminate what to do, what not to do, and why.");
		newBook.setPrice(56f);
		newBook.setIsbn("0321356683");
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2012");
		newBook.setPublishDate(publishDate);
		
		String imagePath = "D:\\BookStoreProject\\dummy-data-books\\books\\Effective Java.jpg";
		
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);
		
		Book updateBook = bookDao.update(newBook);
		
		assertEquals(newBook.getCategory().getCategoryId(), updateBook.getCategory().getCategoryId());
	}

	@Test
	public void testCreateBook() throws ParseException, IOException {
		Book newBook = new Book();
		
		Category category = new Category("Web Design");
		category.setCategoryId(14);
		newBook.setCategory(category);
		
		newBook.setTitle("My test");
		newBook.setAuthor("Tester");
		newBook.setDescription("Test Baby");
		newBook.setPrice(2f);
		newBook.setIsbn("999999X");
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("29/07/2000");
		newBook.setPublishDate(publishDate);
		
		String imagePath = "D:\\BookStoreProject\\dummy-data-books\\books\\Java EE 7 the Big Picture.jpg";
		
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);
		
		Book createBook = bookDao.create(newBook);
		
		assertTrue(createBook.getBookId() > 0);
	}
	
	@Test
	public void testDeleteBookSuccess() {
		int bookId = 6;
		bookDao.delete(bookId);
		
		Book book = bookDao.get(bookId);
		
		assertNull(book);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteBookFail() {
		int bookId = 8;
		bookDao.delete(bookId);
		
		Book book = bookDao.get(bookId);
		
		assertTrue(false);
	}
	
	@Test
	public void testGetBookFail() {
		int bookId = 8;
		Book book = bookDao.get(bookId);
		
		assertNull(book);
	}
	
	@Test
	public void testGetBookSuccess() {
		int bookId = 1;
		Book book = bookDao.get(bookId);
		
		assertNotNull(book);
	}
	
	@Test
	public void testListAll() {
		List<Book> listBook = bookDao.listAll();
		
		for(Book book : listBook) {
			System.out.println(book.getTitle() + " - " + book.getAuthor());
		}
		
		assertFalse(listBook.isEmpty());
	}
	
	@Test
	public void testFindByTitleNotExist() {
		String title = "Thinking Java";
		Book book = bookDao.findByTitle(title);
		
		assertNull(book);
	}
	
	@Test
	public void testFindByTitleExist() {
		String title = "My successful story";
		Book book = bookDao.findByTitle(title);
		
		System.out.println(book.getAuthor() + " - " + book.getPublishDate());
		assertNotNull(book);
	}
	
	@Test
	public void testCountAll() {
		long result = bookDao.count();
		
		assertEquals(5, result);
	}
}
