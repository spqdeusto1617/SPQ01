package db;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.*;
import server.data.*;

public class DAO implements IDAO {

	private PersistenceManagerFactory pmf;

	public DAO(){
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}
	@Override
	public boolean storeUser(User u) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
	    boolean ret=true;
		try {
	       tx.begin();
	      
		       pm.makePersistent(u);
		       tx.commit();
		    } catch (Exception ex) {
		    	System.out.println("   $ Error storing an object: " + ex.getMessage());
		    	ret=false;
		    
		    } finally {
		    	if (tx != null && tx.isActive()) {
		    		tx.rollback();
		    	}

	    		pm.close();
		    }
	    return ret;
	}

	@Override
	public User retrieveUser(String email) {
		// TODO Auto-generated method stub
		User user = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(2);
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			user = pm.getObjectById(User.class, email);
			tx.commit();
		} catch (javax.jdo.JDOObjectNotFoundException jonfe)
		{
			System.out.println("User does not exist: " + jonfe.getMessage());
		}

		finally {
	    	if (tx != null && tx.isActive()) {
	    		tx.rollback();
	    	}

    		pm.close();
	    }

		return user;
	}

	@Override
	public boolean updateUser(User u) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
	    Transaction tx = pm.currentTransaction();
	    boolean r =true;
	    try {
	    	tx.begin();
	    	pm.makePersistent(u);
	    	tx.commit();
	     } catch (Exception ex) {
		   	System.out.println("Error updating a user: " + ex.getMessage());
		   	r=false;
	     } finally {
		   	if (tx != null && tx.isActive()) {
		   		tx.rollback();
		   	}

	   		pm.close();
	     }
	    return r;
	}

	@Override
	public boolean storeReview(Review r) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
	    boolean ret=true;
		try {
	       tx.begin();
	      
		       pm.makePersistent(r);
		       tx.commit();
		    } catch (Exception ex) {
		    	System.out.println("   $ Error storing an object: " + ex.getMessage());
		    	ret=false;
		    
		    } finally {
		    	if (tx != null && tx.isActive()) {
		    		tx.rollback();
		    	}

	    		pm.close();
		    }
	    return ret;
	}

	@Override
	public Review retrieveReview(int id_review) {
		// TODO Auto-generated method stub
		Review review = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(2);
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			review = pm.getObjectById(Review.class, id_review);
			tx.commit();
		} catch (javax.jdo.JDOObjectNotFoundException jonfe)
		{
			System.out.println("Review does not exist: " + jonfe.getMessage());
		}

		finally {
	    	if (tx != null && tx.isActive()) {
	    		tx.rollback();
	    	}

    		pm.close();
	    }

		return review;
	}

	@Override
	public boolean updateReview(Review review) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
	    Transaction tx = pm.currentTransaction();
	    boolean r =true;
	    try {
	    	tx.begin();
	    	pm.makePersistent(review);
	    	tx.commit();
	     } catch (Exception ex) {
		   	System.out.println("Error updating a user: " + ex.getMessage());
		   	r=false;
	     } finally {
		   	if (tx != null && tx.isActive()) {
		   		tx.rollback();
		   	}

	   		pm.close();
	     }
	    return r;
	}

	@Override
	public boolean storeBook(Book b) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
	    boolean ret=true;
		try {
	       tx.begin();
	      
		       pm.makePersistent(b);
		       tx.commit();
		    } catch (Exception ex) {
		    	System.out.println("   $ Error storing an object: " + ex.getMessage());
		    	ret=false;
		    
		    } finally {
		    	if (tx != null && tx.isActive()) {
		    		tx.rollback();
		    	}

	    		pm.close();
		    }
	    return ret;
	}

	@Override
	public Book retrieveBook(int ISBN) {
		// TODO Auto-generated method stub
		Book book = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(2);
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			book = pm.getObjectById(Book.class, ISBN);
			tx.commit();
		} catch (javax.jdo.JDOObjectNotFoundException jonfe)
		{
			System.out.println("Book does not exist: " + jonfe.getMessage());
		}

		finally {
	    	if (tx != null && tx.isActive()) {
	    		tx.rollback();
	    	}

    		pm.close();
	    }

		return book;
	}

	@Override
	public boolean updateBook(Book b) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
	    Transaction tx = pm.currentTransaction();
	    boolean r =true;
	    try {
	    	tx.begin();
	    	pm.makePersistent(b);
	    	tx.commit();
	     } catch (Exception ex) {
		   	System.out.println("Error updating a user: " + ex.getMessage());
		   	r=false;
	     } finally {
		   	if (tx != null && tx.isActive()) {
		   		tx.rollback();
		   	}

	   		pm.close();
	     }
	    return r;
	}

	@Override
	public Book retrieveBookByParameter(String title) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
	    Transaction tx = pm.currentTransaction();
	    pm.getFetchPlan().setMaxFetchDepth(3);
	    Book book = null;
	    
	    try {
	        tx.begin();
	        Extent<Book> extentP = pm.getExtent(Book.class);

	        for (Book p : extentP) {

	            if (p.getTitle().equals(title)) {
	                book = p;
	            }
	        }
	        tx.commit();
	    } catch (Exception ex) {
	        System.out.println("# Error getting Extent: " + ex.getMessage());
	       
	    } finally {
	        if (tx.isActive()) {
	            tx.rollback();
	        }
	        pm.close();
	    }
	    System.out.println(book);
	    return book;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		   PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx = pm.currentTransaction();
	        pm.getFetchPlan().setMaxFetchDepth(3);

	        List<User> users=new ArrayList<>();
	        try {
	            tx.begin();
	            Extent<User> extentP = pm.getExtent(User.class);

	            for (User p : extentP) {

	               users.add(p);
	              
	               
	                }

	            tx.commit();
	        } catch (Exception ex) {
	            System.out.println("# Error getting Extent: " + ex.getMessage());
	        } finally {
	            if (tx.isActive()) {
	                tx.rollback();
	            }
	            pm.close();
	        }

	        return users;
	}

	@Override
	public List<Review> getAllReviews() {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        pm.getFetchPlan().setMaxFetchDepth(3);

        List<Review> reviews=new ArrayList<>();
        try {
            tx.begin();
            Extent<Review> extentP = pm.getExtent(Review.class);

            for (Review p : extentP) {

               reviews.add(p);
              
               
                }

            tx.commit();
        } catch (Exception ex) {
            System.out.println("# Error getting Extent: " + ex.getMessage());
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }

        return reviews;
	}

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        pm.getFetchPlan().setMaxFetchDepth(3);

        List<Book> books=new ArrayList<>();
        try {
            tx.begin();
            Extent<Book> extentP = pm.getExtent(Book.class);

            for (Book p : extentP) {

               books.add(p);
              
               
                }

            tx.commit();
        } catch (Exception ex) {
            System.out.println("# Error getting Extent: " + ex.getMessage());
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }

        return books;
	}

}