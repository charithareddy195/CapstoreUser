package com.capgemini.userstore;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.store.beans.Address;
import com.capgemini.store.beans.Customer;
import com.capgemini.store.beans.Product;
import com.capgemini.store.beans.Wishlist;
import com.capgemini.store.exceptions.InvalidInputException;
import com.capgemini.store.services.CapstoreServices;
import com.capgemini.store.services.CapstoreServicesImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserstoreApplicationTests {
	CapstoreServices capstoreServices;
	@Before
	public void init() {
		capstoreServices = new CapstoreServicesImpl();
		List<Address> address1=(List<Address>) new Address("India","MH","pune",411057,"rajiv","capgemini");
		Customer customer1= new Customer("9912905432","Vamshi krishna", address1,"vamshi@gmail.com","vamshigvk","1234567890543210","who am i", "vamshi");
		
		List<Address> address2=(List<Address>) new Address("India","MH","pune",411057,"rajiv","capgemini");
		Customer customer2= new Customer("9515115200","Charitha", address2,"charitha@gmail.com","chinni","1234567890543211","who am i", "charitha");
		
		List<Address> address3=(List<Address>) new Address("India","MH","pune",411057,"rajiv","capgemini");
		Customer customer3= new Customer("8919488392","pavani", address3,"pavani@gmail.com","pavani","1234567890543212","who am i", "pavani");
		
		List<Address> address4=(List<Address>) new Address("India","TG","Hyderabad",411055,"kphb","capgemini");
		Customer customer4= new Customer("7799421508","namratha", address4,"namratha@gmail.com","namratha","1234567890543213","who am i", "namratha");
		
		List<Address> address5=(List<Address>) new Address("India","TG","Hyderabad",411055,"kphb","capgemini");
		Customer customer5= new Customer("8121221121","karthik", address5,"karthik.mukka@capgemini.com","karthik","1234567890543213","who am i", "Karthik Mukka");
		
		Product product1= new Product(111,"chair", 2300, 5, "chetan");
		Product product2= new Product(112,"table", 300, 3, "woodmaster");
	}
	@Test
	public void addProductToWishlist1() {	
		try {
		//Wishlist wishlist = new Wishlist(1, customer1, product1);
		//capstoreServices.addProductToWishlist(111);
		List<Product> productWishlist= capstoreServices.getWishlist("9912905432");
		} 
		catch (InvalidInputException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void addProductToWishlist2() {
	
	}
}
