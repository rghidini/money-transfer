package com.ghidini.tm.service.test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ghidini.tm.dao.AccountDAO;
import com.ghidini.tm.domain.Account;
import com.ghidini.tm.domain.dto.AccountDTO;
import com.ghidini.tm.service.AccountService;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {
	
	@InjectMocks
	private AccountService service;
	
	@Mock
	private AccountDAO accountDao;

	@Before
	public void init() {
		Mockito.when(accountDao.findById(1L)).thenReturn(new Account(1L,NumberUtils.createBigDecimal("100")));
		Mockito.when(accountDao.findAll()).thenReturn(Arrays.asList(new Account(1L,NumberUtils.createBigDecimal("100"))));
	}

	@Test
	public void findAccountById() {

		Account account = new Account(1L,1L,NumberUtils.createBigDecimal("100"));
		service.addAccount(new Account(1L,NumberUtils.createBigDecimal("100")));
		AccountDTO accountDto = service.findAccountById(1L);
		assertTrue(Objects.equals(account.getClientId(), accountDto.getClientId()));

	}
	
	@Test
	public void findAll() {

		List<AccountDTO> listAccountsDto = service.getAllAccounts();
		assertTrue(CollectionUtils.isNotEmpty(listAccountsDto));

	}
	
	@Test
	public void deleteAccountById() {
		
		verify(accountDao, times(0)).delete(1L);
		
	}
	
	@Test
	public void addAccount() {
		
		verify(accountDao, times(0)).insert(new Account(1L,NumberUtils.createBigDecimal("100")));
		
	}
	
	@Test
	public void updateAccount() {
		
		verify(accountDao, times(0)).update(new Account(1L,NumberUtils.createBigDecimal("100")));
		
	}

}
