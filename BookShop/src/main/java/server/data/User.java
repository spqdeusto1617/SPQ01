package server.data;


import javax.jdo.annotations.*;


import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;



@PersistenceCapable(detachable="true")
public class User implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	private String email;
	private String password;
	private String name;
	private String address;
	private double money=0;	
	private boolean role;		//true --> admin
							 	//false --> user
	
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public User(String email, String password, String name, String address, boolean role) {
		
		this.email = email;
		this.password = password;
		this.name = name;
		this.address = address;
		this.role = role;
		this.money=1000;
	}
	public User(String email, String password, boolean role) {
		// TODO Auto-generated constructor stub
		this.email = email;
		this.password = password;
		this.role = role;
		this.money=1000;
	}
	
	@Persistent(defaultFetchGroup="true", mappedBy="user", dependentElement="true")
	@Join
	List<Review> reviews = new ArrayList<Review>();
	
	@Persistent(defaultFetchGroup="true", dependentElement="true")
	@Join	
	List<Book> books = new ArrayList<Book>();
	
	public void addReview(Review review){
		reviews.add(review);
	}
	public void removeReview(Review review){
		reviews.remove(review);
	}
	public List<Review> getReviews(){
		return this.reviews;
	}
	
	public void addBook(Book book){
		books.add(book);
	}
	public void removeBook(Book book){
		books.remove(book);
	}
	public List<Book> getBooks(){
		return this.books;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
	       this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
	       this.password = password;
	}
		
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
	       this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
	       this.address = address;
	}

	public boolean getRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}
	
	 public String toString() {
		 return "User: email --> " + this.email + ", password -->  " + this.password + ",  User Role -->  " + this.role;
		
        	
	 }
	 
}
	 



	
	

