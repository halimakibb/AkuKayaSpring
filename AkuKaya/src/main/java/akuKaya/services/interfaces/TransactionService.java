package main.java.akuKaya.services.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import main.java.akuKaya.models.Transaction;
import main.java.akuKaya.models.User;

@Service()
public interface TransactionService {
	List<Transaction> getTransactions(String username);
	Transaction getTransaction(int transactionID);
	void insertTransactions(Transaction transaction);
	void deleteTransactions(Transaction transaction);
	User getUser(Transaction transaction);
	
	List<Transaction> filterTransactions(String username, int filterMode, String dateFilter, int monthFilter, int yearMonthFilter, int yearYearlyFilter);
	List<Transaction> getTransactionsByDate(String username, Date localDate);
	List<Transaction> getTransactionsByWeek(String username, Date localDate);
	List<Transaction> getTransactionsByMonth(String username, int month, int year);
	List<Transaction> getTransactionsByYear(String username, int year);
	
	double getTotal(List<Transaction> transactions);
}
