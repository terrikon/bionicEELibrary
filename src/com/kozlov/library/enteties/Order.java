package com.kozlov.library.enteties;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orders")
@NamedQueries({
	@NamedQuery(name="Order.findByLogin", query="SELECT o FROM Order o WHERE o.user.login=:login"),		
	@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")})
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	@ManyToOne
	@JoinColumn(name = "orderStatusId")
	private OrderStatus orderStatus;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date date;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "orderId", referencedColumnName = "orderId")
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();

	public Integer getOrderId() {
		return orderId;
	}

	public User getUser() {
		return user;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public Date getDate() {
		return date;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}
