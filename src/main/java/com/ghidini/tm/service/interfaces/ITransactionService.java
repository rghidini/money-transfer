package com.ghidini.tm.service.interfaces;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import com.ghidini.tm.domain.dto.TransactionDTO;

@Contract
public interface ITransactionService {
	
	public void addTransaction(TransactionDTO transaction);
	public List<TransactionDTO> getAllTransaction();

}
