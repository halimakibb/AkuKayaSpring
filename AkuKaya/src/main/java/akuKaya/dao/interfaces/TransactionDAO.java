package main.java.akuKaya.dao.interfaces;

import java.util.Date;
import java.util.List;

import main.java.akuKaya.models.Transaction;
import main.java.akuKaya.models.User;


public interface TransactionDAO {
	List<Transaction> getTransactions(String username);
	void insertTransactions(Transaction transaction);
	void deleteTransactions(Transaction transaction);
	Transaction getTransaction(int transactionID);
	User getUser(Transaction transaction);
	List<Transaction> getTransactionsByDate(String username, Date searchDate);
	List<Transaction> getTransactionsByWeek(String username, Date searchDate, Date endDate);
	List<Transaction> getTransactionsByMonth(String username, int month, int year);
	List<Transaction> getTransactionsByYear(String username, int year);
}
