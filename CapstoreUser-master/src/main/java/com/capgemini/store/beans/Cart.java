package com.capgemini.store.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cartId;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="phoneNumber")
	private Customer customer;//one to one
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "cart_products", joinColumns = { @JoinColumn(name = "cartId") }, inverseJoinColumns = { @JoinColumn(name = "productId") })
	private List<Product> products = new ArrayList<Product>();// many to many
	/*private int quantityRequired;//max 5
*/	private double totalAmount;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="couponId")
	private Coupon coupon;// one to one
	
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	/*public int getQuantityRequired() {
		return quantityRequired;
	}
	public void setQuantityRequired(int quantityRequired) {
		this.quantityRequired = quantityRequired;
	}*/
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", customer=" + customer + ", products=" + products + ", totalAmount="
				+ totalAmount + ", coupon=" + coupon + "]";
	}

	
	
}

