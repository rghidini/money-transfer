package com.ghidini.tm.service.interfaces;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import com.ghidini.tm.domain.Account;
import com.ghidini.tm.domain.dto.AccountDTO;

@Contract
public interface IAccountService {
	
	public void addAccount(Account account);
	public void deleteAccount(Long id);
	public void updateAccount(Long id, Account account);
	public AccountDTO findAccountById(Long id);
	public List<AccountDTO> getAllAccounts();

}
