package main.java.akuKaya.services.implementations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.akuKaya.dao.interfaces.TransactionDAO;
import main.java.akuKaya.models.Transaction;
import main.java.akuKaya.models.User;
import main.java.akuKaya.services.interfaces.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionDAO transactionDAO;

	@Override
	@Transactional
	public List<Transaction> getTransactions(String username) {
		return transactionDAO.getTransactions(username);
	}

	@Override
	@Transactional
	public void insertTransactions(Transaction transaction) {
		transactionDAO.insertTransactions(transaction);
	}

	@Override
	@Transactional
	public void deleteTransactions(Transaction transaction) {
		transactionDAO.deleteTransactions(transaction);
	}

	@Override
	@Transactional
	public Transaction getTransaction(int transactionID) {
		return transactionDAO.getTransaction(transactionID);

	}

	@Override
	@Transactional
	public User getUser(Transaction transaction) {
		return transactionDAO.getUser(transaction);
	}

	@Override
	
	public List<Transaction> getTransactionsByDate(String username, Date searchDate) {

		return transactionDAO.getTransactionsByDate(username, searchDate);
	}

	@Override
	
	public List<Transaction> getTransactionsByWeek(String username, Date searchDate) {

		LocalDate localDate = searchDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		localDate = localDate.plusDays(7);

		return transactionDAO.getTransactionsByWeek(username, searchDate,
				Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
	}

	@Override
	
	public List<Transaction> getTransactionsByMonth(String username, int month, int year) {

		return transactionDAO.getTransactionsByMonth(username, month, year);
	}

	@Override
	
	public List<Transaction> getTransactionsByYear(String username, int year) {

		return transactionDAO.getTransactionsByYear(username, year);
	}

	@Override
	public double getTotal(List<Transaction> transactions) {

		double total = 0;
		for (Transaction transaction : transactions) {
			if (transaction.getTransactionType() == 1)
				total += transaction.getAmount();
			else
				total -= transaction.getAmount();

		}

		return total;

	}

	@Override
	@Transactional
	public List<Transaction> filterTransactions(String username, int filterMode, String dateFilter, int monthFilter, int yearMonthFilter,
			int yearYearlyFilter) {
		final String inputFormat = "yyyy-MM-dd";
		if (filterMode != 0) {
			switch (filterMode) {
			case 1:

				try {
					return getTransactionsByDate(username,
							new SimpleDateFormat(inputFormat).parse(dateFilter));
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
				break;
			case 2:

				try {
					return getTransactionsByWeek(username,
							new SimpleDateFormat(inputFormat).parse(dateFilter));
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
				break;
			case 3:
				return getTransactionsByMonth(username, monthFilter, yearMonthFilter);
				
			case 4:
				return getTransactionsByYear(username, yearYearlyFilter);
				
			}
		}
		return null;
	}

}
