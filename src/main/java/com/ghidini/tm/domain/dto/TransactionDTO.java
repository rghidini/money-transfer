package com.ghidini.tm.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransactionDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long fromAccountId;

	private Long toAccountId;

	private BigDecimal amount;


	/**
	 * @param fromAccountId
	 * @param toAccountId
	 * @param amount
	 */
	public TransactionDTO(Long fromAccountId, Long toAccountId, BigDecimal amount) {
		super();
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.amount = amount;
	}

	/**
	 * @return the fromAccountId
	 */
	public Long getFromAccountId() {
		return fromAccountId;
	}

	/**
	 * @param fromAccountId the fromAccountId to set
	 */
	public void setFromAccountId(Long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	/**
	 * @return the toAccountId
	 */
	public Long getToAccountId() {
		return toAccountId;
	}

	/**
	 * @param toAccountId the toAccountId to set
	 */
	public void setToAccountId(Long toAccountId) {
		this.toAccountId = toAccountId;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


}
