package db;

import server.data.*;
import java.util.List;

public interface IDAO {

	boolean storeUser(User u);
	User retrieveUser(String email);
	boolean updateUser(User u);

	boolean storeReview(Review r);
	Review retrieveReview(int id_review );
	boolean updateReview(Review r);

	boolean storeBook(Book b);
	Book retrieveBook(int ISBN );
	boolean updateBook(Book b);

	
	
	
	
	
	
	Book retrieveBookByParameter(String title);
	

	//Get all entities

	public List<User> getAllUsers();
	public List<Review> getAllReviews();
	public List<Book> getAllBooks();


}