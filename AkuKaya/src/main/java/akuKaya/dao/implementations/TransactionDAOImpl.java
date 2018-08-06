package main.java.akuKaya.dao.implementations;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.java.akuKaya.dao.interfaces.TransactionDAO;
import main.java.akuKaya.models.Transaction;
import main.java.akuKaya.models.User;

@Repository
public class TransactionDAOImpl implements TransactionDAO {

	@Autowired
	private SessionFactory factory;

	@Override
	public List<Transaction> getTransactions(String username) {

		// get the current hibernate session
		Session currentSession = factory.getCurrentSession();

		// create a query
		Query<Transaction> query = currentSession.createQuery("from Transaction as transaction "
				+ "where transaction.user.username=:username " + "and isActive = true " + "order by transactionDate",
				Transaction.class);

		query.setParameter("username", username);
		// execute query and get result list
		List<Transaction> transactions = query.getResultList();

		// return results

		return transactions;
	}

	@Override
	public void insertTransactions(Transaction transaction) {

		// get the current hibernate session
		Session currentSession = factory.getCurrentSession();

		// save the customer
		currentSession.saveOrUpdate(transaction);

	}

	@Override
	public void deleteTransactions(Transaction transaction) {
		// get the current hibernate session
		Session currentSession = factory.getCurrentSession();

		// save the customer
		currentSession.saveOrUpdate(transaction);
	}

	@Override
	public Transaction getTransaction(int transactionID) {
		// get the current hibernate session
		Session currentSession = factory.getCurrentSession();

		// create a query
		Query<Transaction> query = currentSession.createQuery("from Transaction as transaction "
				+ "where transactionID=:transactionID " + "and isActive = true " + "order by transactionDate",
				Transaction.class);

		query.setParameter("transactionID", transactionID);
		// execute query and get result list
		List<Transaction> transactions = query.getResultList();
		Transaction transaction = transactions.get(0);
		// return results

		return transaction;

	}

	@Override
	public User getUser(Transaction transaction) {
		// get the current hibernate session
		Session currentSession = factory.getCurrentSession();

		// create a query
		Query<User> query = currentSession
				.createQuery("from User as user " + "where userID=:userID " + "and isActive = true ", User.class);

		query.setParameter("userID", transaction.getUser().getUserID());
		// execute query and get result list
		List<User> users = query.getResultList();
		User user = users.get(0);
		// return results

		return user;
	}

	@Override
	public List<Transaction> getTransactionsByDate(String username, Date searchDate) {
		// get the current hibernate session
		Session currentSession = factory.getCurrentSession();

		// create a query
		Query<Transaction> query = currentSession.createQuery(
				"from Transaction " 
						+ "where transactionDate=:searchDate "
						+ "and user.username=:username " 
						+ "and isActive = true ",
				Transaction.class);

		query.setParameter("searchDate", searchDate);
		query.setParameter("username", username);
		// execute query and get result list
		List<Transaction> transactions = query.getResultList();

		return transactions;
	}

	@Override
	public List<Transaction> getTransactionsByWeek(String username, Date searchDate, Date endDate) {
		// get the current hibernate session
		Session currentSession = factory.getCurrentSession();

		// create a query
		Query<Transaction> query = currentSession.createQuery(
				"from Transaction " 
						+ "where transactionDate "
						+ "between :searchDate and :endDate "
						+ "and user.username=:username " 
						+ "and isActive = true ",
				Transaction.class);

		query.setParameter("searchDate", searchDate);

		query.setParameter("endDate", endDate);
		query.setParameter("username", username);
		// execute query and get result list
		List<Transaction> transactions = query.getResultList();

		return transactions;
	}

	@Override
	public List<Transaction> getTransactionsByMonth(String username, int month, int year) {
		// get the current hibernate session
		Session currentSession = factory.getCurrentSession();

		// create a query
		Query<Transaction> query = currentSession.createQuery(
				"from Transaction " 
						+ "where month(transactionDate) = :month "
						+ "and year(transactionDate) = :year "
						+ "and user.username=:username " 
						+ "and isActive = true ",
				Transaction.class);

		query.setParameter("month", month);		
		query.setParameter("year", year);
		query.setParameter("username", username);
		// execute query and get result list
		List<Transaction> transactions = query.getResultList();

		return transactions;
	}

	@Override
	public List<Transaction> getTransactionsByYear(String username, int year) {
		// get the current hibernate session
		Session currentSession = factory.getCurrentSession();

		// create a query
		Query<Transaction> query = currentSession.createQuery(
				"from Transaction " 
						+ "where year(transactionDate)=:year "
						+ "and user.username=:username " 
						+ "and isActive = true ",
				Transaction.class);

	
		query.setParameter("year", year);
		query.setParameter("username", username);
		// execute query and get result list
		List<Transaction> transactions = query.getResultList();

		return transactions;
	}

}
