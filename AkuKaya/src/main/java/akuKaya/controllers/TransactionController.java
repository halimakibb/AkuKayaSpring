package main.java.akuKaya.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import main.java.akuKaya.forms.TransactionFilterForm;
import main.java.akuKaya.forms.TransactionForm;
import main.java.akuKaya.models.FormMessage;
import main.java.akuKaya.models.Transaction;
import main.java.akuKaya.models.User;
import main.java.akuKaya.services.interfaces.UserService;
import main.java.akuKaya.services.interfaces.PaginationService;
import main.java.akuKaya.services.interfaces.TransactionService;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

	private static int currentPage = 1;
	private static int pageSize = 5;
	private static int filterMode = 0;
	private static String dateFilter = "";
	private static int monthFilter = 0;
	private static int yearMonthFilter = 0;
	private static int yearYearlyFilter = 0;

	@Autowired
	HttpSession httpSession;

	@Autowired
	UserService userService;

	@Autowired
	TransactionService transactionService;

	@Autowired
	PaginationService paginationService;

	@GetMapping("/")
	public String showTransactions(TransactionFilterForm transactionFilterForm, Model model, Authentication auth,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
			@RequestParam("timeFilter") Optional<Integer> timeFilter,
			@RequestParam("dailyFilter") Optional<String> dailyFilter,
			@RequestParam("monthlyFilterMonth") Optional<Integer> monthlyFilterMonth,
			@RequestParam("monthlyFilterYear") Optional<Integer> monthlyFilterYear,
			@RequestParam("yearlyFilter") Optional<Integer> yearFilter) {

		page.ifPresent(p -> currentPage = p);
		size.ifPresent(s -> pageSize = s);
		timeFilter.ifPresent(mode -> filterMode = mode);
		dailyFilter.ifPresent(date -> dateFilter = date);
		monthlyFilterMonth.ifPresent(month -> monthFilter = month);
		monthlyFilterYear.ifPresent(year -> yearMonthFilter = year);
		yearFilter.ifPresent(yearly -> yearYearlyFilter = yearly);
		
		List<String> paramNames = new ArrayList<>();
        paramNames.add("timeFilter");
        paramNames.add("dailyFilter");
        paramNames.add("monthlyFilterMonth");
        paramNames.add("monthlyFilterYear");
        paramNames.add("yearFilter");

        model.addAttribute("timeFilter", filterMode);
        model.addAttribute("dailyFilter", dateFilter);
        model.addAttribute("monthlyFilterMonth", monthFilter);
        model.addAttribute("monthlyFilterYear", yearMonthFilter);
        model.addAttribute("yearFilter", yearYearlyFilter);
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		if (filterMode == 0)
			transactions = transactionService.getTransactions(auth.getName());
		else
			transactions = transactionService.filterTransactions(auth.getName(), filterMode, dateFilter, monthFilter, yearMonthFilter, yearYearlyFilter);

		Page<Transaction> transactionPage = paginationService
				.findPaginatedTransaction(PageRequest.of(currentPage - 1, pageSize), transactions);
		List<Integer> pageNumbers = paginationService.findPageNumber(transactionPage);

		double total = transactionService.getTotal(transactions);
		FormMessage formMessage = new FormMessage(Double.toString(total));

		if (pageNumbers != null)
			model.addAttribute("pageNumbers", pageNumbers);

		model.addAttribute("paramNames", paramNames);
		model.addAttribute("formMessage", formMessage);
		model.addAttribute("transactionPage", transactionPage);

		return "/transactions/list";

	}

	@GetMapping("/insert")
	public String showForm(TransactionForm transactionForm, Model model) {

		Transaction transaction = new Transaction();
		model.addAttribute("transaction", transaction);
		return "/transactions/insert_transaction";

	}

	@PostMapping("/insert")
	public String insertTransaction(@Valid TransactionForm transactionForm, BindingResult bindingResult, Model model,
			Authentication auth) {

		if (bindingResult.hasErrors()) {
			Transaction transaction = new Transaction();
			model.addAttribute("transaction", transaction);
			return "/transactions/insert_transaction";
		}

		User user = userService.getUser(auth.getName());

		// transaction detail

		String description = transactionForm.getDescription();
		double amount = transactionForm.getAmount();
		int transactionType = transactionForm.getTransactionType();
		Date transactionDate = transactionForm.getTransactionDate();

		// create new transaction
		Transaction newTransaction = new Transaction(user, description, amount, transactionType, transactionDate, true,
				user.getUsername(), new Date(), user.getUsername(), new Date());

		// save transaction
		transactionService.insertTransactions(newTransaction);

		return "redirect:/transaction/";

	}

	@GetMapping("/update")
	public String showFormUpdate(@RequestParam("transactionID") int transactionID, Model model,
			TransactionForm transactionForm, Authentication auth) {

		Transaction transaction = transactionService.getTransaction(transactionID);

		if (transaction != null) {

			User user = transactionService.getUser(transaction);

			if (user.getUsername().equals(auth.getName())) {
				model.addAttribute("transaction", transaction);
				return "/transactions/insert_transaction";
			}

			return "redirect:/transaction/";

		}

		return "redirect:/transaction/";

	}

	@PostMapping("/update")
	public String updateTransaction(@Valid TransactionForm transactionForm, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			Transaction transaction = transactionService.getTransaction(transactionForm.getTransactionID());
			model.addAttribute("transaction", transaction);
			return "/transactions/insert_transaction";
		}

		// transaction detail
		//
		String description = transactionForm.getDescription();
		double amount = transactionForm.getAmount();
		int transactionType = transactionForm.getTransactionType();
		Date transactionDate = transactionForm.getTransactionDate();
		// update transaction
		Transaction transaction = transactionService.getTransaction(transactionForm.getTransactionID());
		//

		transaction.setDescription(description);
		transaction.setAmount(amount);
		transaction.setTransactionType(transactionType);
		transaction.setTransactionDate(transactionDate);
		transaction.setUpdatedDate(new Date());

		// // save transaction
		transactionService.insertTransactions(transaction);

		return "redirect:/transaction/";

	}

	@GetMapping("/delete")
	public String deleteTransaction(@RequestParam("transactionID") int transactionID, Authentication auth) {

		Transaction transaction = transactionService.getTransaction(transactionID);

		User user = transactionService.getUser(transaction);

		if (user.getUsername().equals(auth.getName())) {
			transaction.setActive(false);

			transactionService.insertTransactions(transaction);
			return "redirect:/transaction/";

		}

		return "redirect:/users/login";
	}

}
