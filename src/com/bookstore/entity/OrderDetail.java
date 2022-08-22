package com.bookstore.entity;
// Generated Jun 28, 2022 9:58:52 AM by Hibernate Tools 5.2.12.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OrderDetail generated by hbm2java
 */
@Entity
@Table(name = "order_detail", catalog = "bookstoredb")
public class OrderDetail implements java.io.Serializable {

	private OrderDetailId id;
	private Book book;
	private BookOrder bookOrder;

	public OrderDetail() {
	}

	public OrderDetail(OrderDetailId id) {
		this.id = id;
	}

	public OrderDetail(OrderDetailId id, Book book, BookOrder bookOrder) {
		this.id = id;
		this.book = book;
		this.bookOrder = bookOrder;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "orderId", column = @Column(name = "order_id")),
			@AttributeOverride(name = "bookId", column = @Column(name = "book_id")),
			@AttributeOverride(name = "quantity", column = @Column(name = "quantity", nullable = false)),
			@AttributeOverride(name = "subtotal", column = @Column(name = "subtotal", nullable = false, precision = 12, scale = 0)) })
	public OrderDetailId getId() {
		return this.id;
	}

	public void setId(OrderDetailId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id", insertable = false, updatable = false)
	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", insertable = false, updatable = false)
	public BookOrder getBookOrder() {
		return this.bookOrder;
	}

	public void setBookOrder(BookOrder bookOrder) {
		this.bookOrder = bookOrder;
	}

}
