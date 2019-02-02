package com.ghidini.tm.service.test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

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
import com.ghidini.tm.dao.TransactionDAO;
import com.ghidini.tm.domain.Transaction;
import com.ghidini.tm.domain.dto.AccountDTO;
import com.ghidini.tm.domain.dto.TransactionDTO;
import com.ghidini.tm.service.AccountService;
import com.ghidini.tm.service.TransactionService;

@RunWith(MockitoJUnitRunner.class)
public class TransactionTest {
	
	@InjectMocks
	private TransactionService service;
	
	@Mock
	private TransactionDAO transactionDao;
	
	@Mock
	private AccountService accountService;
	
	@Mock
	private AccountDAO accountDao;

	@Before
	public void init() {
		Mockito.when(transactionDao.findAll()).thenReturn(Arrays.asList(new Transaction(1L, 2L, NumberUtils.createBigDecimal("100"))));
		Mockito.when(accountService.findAccountById(1L)).thenReturn(new AccountDTO(1L,NumberUtils.createBigDecimal("100")));
		Mockito.when(accountService.findAccountById(2L)).thenReturn(new AccountDTO(2L,NumberUtils.createBigDecimal("100")));
	}

	@Test
	public void findAll() {

		List<TransactionDTO> listTransactionsDto = service.getAllTransaction();
		assertTrue(CollectionUtils.isNotEmpty(listTransactionsDto));

	}
	
	@Test
	public void addTransaction() {
		service.addTransaction(new Transaction(1L, 2L, NumberUtils.createBigDecimal("100")));
		List<TransactionDTO> listTransactionsDto = service.getAllTransaction();
		assertTrue(CollectionUtils.isNotEmpty(listTransactionsDto));
	}
	
	@Test(expected = IllegalArgumentException.class)
    public void shouldThrowException_whenAmountIsNegative() {
        service.addTransaction(new Transaction(1L, 2L, NumberUtils.createBigDecimal("-100")));
    }
	
}
