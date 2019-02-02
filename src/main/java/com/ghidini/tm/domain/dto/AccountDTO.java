package com.ghidini.tm.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long clientId;
	
	private BigDecimal amount;

	/**
	 * @param clientId
	 * @param amount
	 */
	public AccountDTO(Long clientId, BigDecimal amount) {
		super();
		this.clientId = clientId;
		this.amount = amount;
	}

	/**
	 * @return the clientId
	 */
	public Long getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(Long clientId) {
		this.clientId = clientId;
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
