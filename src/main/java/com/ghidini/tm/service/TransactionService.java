package com.ghidini.tm.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.math.NumberUtils;
import org.jvnet.hk2.annotations.Service;

import com.ghidini.tm.dao.TransactionDAO;
import com.ghidini.tm.domain.Account;
import com.ghidini.tm.domain.Transaction;
import com.ghidini.tm.domain.dto.AccountDTO;
import com.ghidini.tm.domain.dto.TransactionDTO;
import com.ghidini.tm.service.interfaces.ITransactionService;

@Service
public class TransactionService implements ITransactionService{


	private static final String SAME_ACCOUNTS = "The accounts need to be different";
	private static final String TRANSFER_VALUE = "Transfer value exceeds what is available on account - ";
	private static final String AMOUNT_GREATER_THAN_ZERO = "Amount must be greater than or equal zero";

	private AccountService accountService = new AccountService();

	private TransactionDAO transactionDao = new  TransactionDAO();


	@Override
	public void addTransaction(TransactionDTO transaction) {

		if(Objects.equals(transaction.getFromAccountId(), transaction.getToAccountId())) {
			throw new IllegalArgumentException(SAME_ACCOUNTS);
		}

		BigDecimal amount = Optional.of(transaction.getAmount())
				.filter(Objects::nonNull)
				.filter(a -> NumberUtils.isNumber(a.toString()))
				.filter(a -> Objects.equals(a.signum(), NumberUtils.INTEGER_ONE))
				.orElseThrow(() -> new IllegalArgumentException(AMOUNT_GREATER_THAN_ZERO));

		AccountDTO fromAccount = accountService.findAccountById(transaction.getFromAccountId());
		AccountDTO toAccount = accountService.findAccountById(transaction.getToAccountId());

		BigDecimal subtract = fromAccount.getAmount().subtract(amount);
		if(Objects.equals(subtract.signum(), NumberUtils.INTEGER_MINUS_ONE)) {
			throw new IllegalArgumentException(TRANSFER_VALUE + fromAccount.getAmount());
		}

		fromAccount.setAmount(subtract);
		accountService.updateAccount(transaction.getFromAccountId(), new Account(fromAccount.getClientId(), fromAccount.getAmount()));

		BigDecimal add = toAccount.getAmount().add(amount);
		toAccount.setAmount(add);
		accountService.updateAccount(transaction.getToAccountId(), new Account(toAccount.getClientId(), toAccount.getAmount()));

		transactionDao.insert(new Transaction(transaction.getFromAccountId(), transaction.getToAccountId(), 
				transaction.getAmount(), LocalDateTime.now()));
		
	}

	@Override
	public List<TransactionDTO> getAllTransaction() {
		List<TransactionDTO> listTransactionsDto = new ArrayList<>();
		Optional.ofNullable(transactionDao.findAll())
		.orElseGet(Collections::emptyList)
		.stream()
		.filter(Objects::nonNull)
		.forEach(transaction -> listTransactionsDto.add(new TransactionDTO(transaction.getFromAccountId(), transaction.getToAccountId(), 
				transaction.getAmount(), transaction.getTransactionDate())));
		return listTransactionsDto;
	}

}
