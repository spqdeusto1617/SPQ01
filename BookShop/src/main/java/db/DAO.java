package db;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import server.data.*;

public class DAO implements IDAO {

	private PersistenceManagerFactory pmf;
	final static  Logger logger = LoggerFactory.getLogger(DAO.class);

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
		User userCopy = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(2);
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			user = pm.getObjectById(User.class, email);
			userCopy = (User)pm.detachCopy(user);
			tx.commit();
		} catch (javax.jdo.JDOObjectNotFoundException jonfe)
		{

		}

		finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return userCopy;
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
	public Review retrieveReview(int id_review) {
		// TODO Auto-generated method stub
		Review review = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			review = pm.getObjectById(Review.class, id_review);
			tx.commit();
		} catch (javax.jdo.JDOObjectNotFoundException jonfe)
		{

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
		Book bookCopy = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(2);
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			book = pm.getObjectById(Book.class, ISBN);
			bookCopy = (Book)pm.detachCopy(book);
			tx.commit();
		} catch (javax.jdo.JDOObjectNotFoundException jonfe)
		{

		}

		finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return bookCopy;
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


		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		logger.info(book.toString());
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

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return books;
	}
	@Override
	public void deleteReview(Review r) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		tx.begin();
		pm.deletePersistent(r);
		tx.commit();
	}
	@Override
	public void deleteBook(Book b) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		tx.begin();
		pm.deletePersistent(b);
		tx.commit();

	}

	/*
	public  boolean deleteDatabase() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        boolean r = true;
        try
        {
            tx.begin();

            logger.info("Deleting test users from persistence. Cleaning up.");
            Query<User> q1 = pm.newQuery(User.class);
            Query<Book> q2 = pm.newQuery(Book.class);
            Query<Review> q3 = pm.newQuery(Review.class);
            	q1.deletePersistentAll();
            	q2.deletePersistentAll();
            	q3.deletePersistentAll();


            tx.commit();

        }
        catch(Exception ex) {
            logger.error("# Error deleting DB: " + ex.getMessage());
            r = false;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
        return r;

	}
	 */
}
