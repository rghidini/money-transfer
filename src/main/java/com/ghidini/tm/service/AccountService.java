package com.ghidini.tm.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.math.NumberUtils;
import org.jvnet.hk2.annotations.Service;

import com.ghidini.tm.dao.AccountDAO;
import com.ghidini.tm.domain.Account;
import com.ghidini.tm.domain.dto.AccountDTO;
import com.ghidini.tm.exceptions.IdNotFoundException;
import com.ghidini.tm.service.interfaces.IAccountService;

@Service
public class AccountService implements IAccountService{

	private static final String ACCOUNT_NOT_FOUND = "Account not found";
	private static final String ID_CANT_BE_NULL = "Id can't be null and must be a positive number";
	private static final String AMOUNT_GREATER_THAN_ZERO = "Amount must be greater than or equal zero";

	private AccountDAO accountDao = new AccountDAO();

	@Override
	public void addAccount(Account account) {
		accountDao.insert(new Account(verifyId(account.getClientId()), verifyAmount(account.getAmount())));
	}

	@Override
	public AccountDTO findAccountById(Long id) {
		AccountDTO accountDto = null;
		Account account = verifyAccount(verifyId(id));
		accountDto = new AccountDTO(account.getClientId(), account.getAmount());
		return accountDto;
	}

	@Override
	public List<AccountDTO> getAllAccounts() {
		List<AccountDTO> listAccountsDto = new ArrayList<>();
		Optional.ofNullable(accountDao.findAll())
		.orElseGet(Collections::emptyList)
		.stream()
		.filter(Objects::nonNull)
		.forEach(account -> listAccountsDto.add(new AccountDTO(account.getClientId(), account.getAmount())));
		return listAccountsDto;
	}

	@Override
	public void deleteAccount(Long id) {
		accountDao.delete(verifyAccount(verifyId(id)).getAccountId());
	}

	@Override
	public void updateAccount(Long id, Account account) {
		accountDao.update(new Account(verifyAccount(verifyId(id)).getAccountId(), verifyId(account.getClientId()), verifyAmount(account.getAmount())));		
	}

	private Long verifyId(Long id) {
		return Optional.ofNullable(id)
				.filter(i -> NumberUtils.isNumber(i.toString()))
				.filter(i -> Objects.equals(Long.signum(i), NumberUtils.INTEGER_ONE))
				.orElseThrow(() -> new IllegalArgumentException(ID_CANT_BE_NULL));
	}

	private Account verifyAccount(Long id) {
		return Optional.ofNullable(accountDao.findById(id))
				.orElseThrow(() -> new IdNotFoundException(ACCOUNT_NOT_FOUND));
	}

	private BigDecimal verifyAmount(BigDecimal amount) {
		return Optional.ofNullable(amount)
				.filter(a -> NumberUtils.isNumber(a.toString()))
				.filter(a -> Objects.equals(a.signum(), NumberUtils.INTEGER_ONE))
				.orElseThrow(() -> new IllegalArgumentException(AMOUNT_GREATER_THAN_ZERO));
	}


}
