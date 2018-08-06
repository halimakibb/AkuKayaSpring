package main.java.akuKaya.forms;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TransactionFilterForm {
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date dailyFilter;
	
	private int monthlyFilterMonth;
	
	private int monthlyFilterYear;
	
	private int yearlyFilter;
	
	private int timeFilter;

	public Date getDailyFilter() {
		return dailyFilter;
	}

	public void setDailyFilter(Date dailyFilter) {
		this.dailyFilter = dailyFilter;
	}

	public int getMonthlyFilterMonth() {
		return monthlyFilterMonth;
	}

	public void setMonthlyFilterMonth(int monthlyFilterMonth) {
		this.monthlyFilterMonth = monthlyFilterMonth;
	}

	public int getMonthlyFilterYear() {
		return monthlyFilterYear;
	}

	public void setMonthlyFilterYear(int monthlyFilterYear) {
		this.monthlyFilterYear = monthlyFilterYear;
	}

	public int getYearlyFilter() {
		return yearlyFilter;
	}

	public void setYearlyFilter(int yearlyFilter) {
		this.yearlyFilter = yearlyFilter;
	}

	public int getTimeFilter() {
		return timeFilter;
	}

	public void setTimeFilter(int timeFilter) {
		this.timeFilter = timeFilter;
	}
	
	
}
