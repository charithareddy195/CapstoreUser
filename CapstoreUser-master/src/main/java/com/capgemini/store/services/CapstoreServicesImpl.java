package com.capgemini.store.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.store.beans.Address;
import com.capgemini.store.beans.Cart;
import com.capgemini.store.beans.Category;
import com.capgemini.store.beans.Coupon;
import com.capgemini.store.beans.Customer;
import com.capgemini.store.beans.Discount;
import com.capgemini.store.beans.Orders;
import com.capgemini.store.beans.Product;
import com.capgemini.store.beans.Review;
import com.capgemini.store.beans.Wishlist;
import com.capgemini.store.exceptions.CustomerNotFoundException;
import com.capgemini.store.exceptions.InvalidInputException;
import com.capgemini.store.reposervices.AddressRepo;
import com.capgemini.store.reposervices.CartRepo;
import com.capgemini.store.reposervices.CategoryRepo;
import com.capgemini.store.reposervices.CouponRepo;
import com.capgemini.store.reposervices.CustomerRepo;
import com.capgemini.store.reposervices.DiscountRepo;
import com.capgemini.store.reposervices.OrdersRepo;
import com.capgemini.store.reposervices.ProductRepo;
import com.capgemini.store.reposervices.ReviewRepo;
import com.capgemini.store.reposervices.WishlistRepo;
@Component(value="capstoreServices")
public class CapstoreServicesImpl implements CapstoreServices{
	
	Customer customer;
	Review review;
	Product product;
	
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired 
	private ProductRepo productRepo;
	@Autowired
	private CartRepo cartRepo;
	@Autowired
	private ReviewRepo reviewRepo;
	@Autowired
	private OrdersRepo ordersRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private WishlistRepo wishlistRepo;
	@Autowired
	private AddressRepo addressRepo;
	@Autowired
	private DiscountRepo discountRepo;
	@Autowired
	private CouponRepo couponRepo;



	
	@Override
	public Customer customerSignIn(String email, String inputPassword) throws InvalidInputException {
		Customer customer=customerRepo.findByEmail(email);
		if(customer!=null){
			String password=customer.getPassword();
			System.out.println(password);
			if(password.equals(inputPassword)){
				return customer;
			}
			else{
				throw new InvalidInputException("Incorrect Password");
			}
		}
		else{
			throw new InvalidInputException("Account with this email doesnot exist");
		}
	}
	@Override
	public Customer getCustomerDetails(String phoneNumber) throws InvalidInputException {
		System.out.println("in");
		return customerRepo.getOne(phoneNumber);
	}
	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}
	@Override
	public Product getProductById(int productId) throws InvalidInputException {
		return productRepo.findByProductId(productId);
	}
	@Override
	public void setReviewMethod(String phoneNumber,int rating,String comments,int productId) throws InvalidInputException {
		Review review=new Review();
		review.getProduct().setProductId(productId);
		review.getCustomer().setPhoneNumber(phoneNumber);
		review.setComments(comments);
		review.setProductRating(rating);
		//saving reviews in customer
		Customer customer=customerRepo.getOne(phoneNumber);
		List<Review> customerReviewList =customer.getReviews();
		customerReviewList.add(review);
		customer.setReviews(customerReviewList);
		customerRepo.save(customer);
		//saving reviews in product
		Product product=productRepo.getOne(productId);
		List<Review> productReviewList=product.getReview();
		productReviewList.add(review);
		product.setReview(productReviewList);
		productRepo.save(product);
	}
	@Override
	public String getDeliveryStatus(int orderId) throws InvalidInputException {
		Orders order=ordersRepo.getOne(orderId);
		return order.getDeliveryStatus();
	}
	@Override
	public List<Product> getProductByCategory(Category category) throws InvalidInputException {
		String categoryName=category.getCategoryName();
		category=categoryRepo.findByCategoryName(categoryName);
		return category.getProducts();
	}
	@Override
	public boolean addProductToWishlist(String phoneNumber,int productId) throws InvalidInputException {
		Product product=productRepo.getOne(productId);
		Wishlist wishlist=wishlistRepo.findByCustomer(phoneNumber);
		List<Product> productsList=wishlist.getProducts();
		productsList.add(product);
		wishlist.setProducts(productsList);
		wishlistRepo.save(wishlist);
		return true;
	}
	@Override
	public boolean removeProductFromWishlist(String phoneNumber,int productId) throws InvalidInputException {
		Product product=productRepo.getOne(productId);
		Wishlist wishlist=wishlistRepo.findByCustomer(phoneNumber);
		List<Product> productsList=wishlist.getProducts();
		productsList.remove(product);
		wishlist.setProducts(productsList);
		wishlistRepo.save(wishlist);
		return true;
	}
	@Override
	public List<Product> getWishlist(String phoneNumber) throws InvalidInputException {
		Wishlist wishlist=wishlistRepo.findByCustomer(phoneNumber);
		return wishlist.getProducts();
	}
	@Override
	public boolean updateSecurityQuestion(String phoneNumber,String securityquestion) throws InvalidInputException {
		Customer customer=customerRepo.getOne(phoneNumber);
		customer.setSecurityQuestion(securityquestion);
		customerRepo.save(customer);
		return true;
	}
	@Override
	public boolean updateSecurityAnswer(String phoneNumber,String securityAnswer) throws InvalidInputException {
		Customer customer=customerRepo.getOne(phoneNumber);
		customer.setSecurityAnswer(securityAnswer);
		customerRepo.save(customer);
		return true;
	}
	@Override
	public boolean updateCardNumber(String phoneNumber,String cardNumber) throws InvalidInputException {
		Customer customer=customerRepo.getOne(phoneNumber);
		customer.setCardNumber(cardNumber);
		customerRepo.save(customer);
		return true;
	}
	@Override
	public boolean updateCustomerName(String phoneNumber,String customerName) throws InvalidInputException {
		Customer customer=customerRepo.getOne(phoneNumber);
		customer.setCustomerName(customerName);
		customerRepo.save(customer);
		return true;
	}
	//------------------
	@Override
	public Customer signUp(Customer customer) {
		return customerRepo.save(customer);
	}

	@Override
	public String forgotPassword(String mobileNumber) throws CustomerNotFoundException{


		customer = customerRepo.getOne(mobileNumber);
		if(customer==null)
			throw new CustomerNotFoundException("customer not found with mobile no.");
		else
			return customer.getSecurityQuestion();
	}

	@Override//encryption 
	public String securityQuestion(String securityAnswer) throws InvalidInputException {
		if(securityAnswer.equals(customer.getSecurityAnswer()))
		{
			return customer.getPassword();
		}
		else
			throw new InvalidInputException("Invalid answer");
	}

	@Override
	public void onlinePayment(String cardNumber, String customerPhoneNumber) {
		Customer cust = new Customer();
		cust = customerRepo.getOne(customerPhoneNumber);
		cust.setCardNumber(cardNumber);
		customerRepo.save(cust);

	}

	@Override
	public void addToCart(int productId, int cartId, int quantity) {
		Product product= productRepo.getOne(productId);
		Cart cart = cartRepo.getOne(cartId);
		List<Product> products =cart.getProducts();
		product.setCartQuantity(quantity);
		products.add(product);
		cart.setProducts(products);
		cartRepo.save(cart);
	}

	@Override
	public Cart updateCart(int productId, int cartId, int quantity) {
		Cart cart = cartRepo.getOne(cartId);
		List<Product> products =cart.getProducts();
		int productIndex= products.indexOf(new Product(productId));
		Product product= products.get(productIndex);
		product.setCartQuantity(quantity);
		products.set(productIndex, product);
		cart.setProducts(products);
		return cartRepo.save(cart);
	}

	@Override
	public Cart removeProductFromCart(int productId, int cartId) {
		Cart cart = cartRepo.getOne(cartId);
		List<Product> products =cart.getProducts();
		int productIndex =products.indexOf(new Product(productId));
		products.remove(productIndex);
		cart.setProducts(products);
		return cartRepo.save(cart);

	}

	@Override
	public void addAddress(Address address) {
		addressRepo.save(address);
	}

	@Override
	public Address getAddress(int addressId) {
		return addressRepo.getOne(addressId);
	}

/*MERCHANT	
 @Override
	public void updateQuantity(int productId, int quantityOrdered, int orderId) {
		Orders order = ordersRepo.getOne(orderId);
		List<Product> products =order.getProducts();
		int productIndex =products.indexOf(new Product(productId));
		Product product =products.get(productIndex);
		int quantityLeft=0;
		if(order.getDeliveryStatus()!=null && order.isRefundRequest()==false)
		{
			quantityLeft = product.getProductQuantityAvailable()-quantityOrdered;
			if(quantityLeft==0)
			{
				product.setProductStatus(true);// product is now out of stock
			}
			product.setProductQuantityAvailable(quantityLeft);
			productRepo.save(product);
		}
		if(order.isRefundRequest()==true)
		{
			if(isRefundRequestValid(order))
			{
				quantityLeft = product.getProductQuantityAvailable()+quantityOrdered;
				product.setProductQuantityAvailable(quantityLeft);
				product.setProductStatus(false); // product is in stock
				productRepo.save(product);

			}
		}

	}*/

	public boolean isRefundRequestValid(Orders order) {
		Date date1 =order.getElligibleReturnDate();
		Date date2 = order.getRefundRequestDate();
		if(date1.after(date2))
		{return false;}
		else
			return true;


	}

	@Override //if discount is valid, we will get the discounted product price  
	//else we get the original product price
	public double applyDiscount(int productId) {
		Product product =productRepo.getOne(productId);
		Discount discount = product.getDiscount();
		double price = product.getProductPrice();
		double finalPrice=price;
		if(discountIsValid(discount))
		{
			double percentDiscount =discount.getPercentDiscount();
			finalPrice=price-((price*percentDiscount)/100);
			product.setProductPrice(finalPrice);
		}
		return finalPrice;
	}

	public boolean discountIsValid(Discount discount) {
		Date date2 = discount.getEndDateOfDiscount();
		Date date1 = discount.getStartDateOfDiscount();
		if(date1.before(new Date()))
		{
			if(date2.after(new Date()))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	//if coupon is valid, we will get the discounted cart price  
	//else we get the original cart price
	public double applyCoupon(int cartId) {
		Cart cart = cartRepo.getOne(cartId);
		Coupon coupon = cart.getCoupon();
		double cartAmount = cart.getTotalAmount();
		double finalPrice = cartAmount; 
		if(couponIsValid(coupon))
		{
			double couponDiscount = coupon.getCouponDiscountValue();
			finalPrice=cartAmount-((cartAmount*couponDiscount)/100);
			cart.setTotalAmount(finalPrice);
		}
		return finalPrice;
	}

	public boolean couponIsValid(Coupon coupon) {
		Date date2 = coupon.getCouponEndDate();
		Date date1 = coupon.getCouponStartDate();
		if(date1.before(new Date()))
		{
			if(date2.after(new Date()))
			{
				return true;
			}
		}
		return false;
	}
}
