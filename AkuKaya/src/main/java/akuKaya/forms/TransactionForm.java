package main.java.akuKaya.forms;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class TransactionForm {

	private int transactionID;

	@NotNull
	@Size(min = 1, max = 250)
	private String description;

	@Min(value = 1)
	private double amount;

	@NotNull
	private int transactionType;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date transactionDate;

	public String getDescription() {
		return description;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

}
