package com.ghidini.tm.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ACCOUNT")
public class Account implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_ACCOUNT", allocationSize = 1, sequenceName = "SEQ_ACCOUNT")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACCOUNT")
	@Column(name = "ACCOUNT_ID")
	private Long accountId;
	
	@Column(name = "CLIENT_ID", nullable = false)
	private Long clientId;
	
	@Column(name = "AMOUNT", nullable = false)
	private BigDecimal amount;

	/**
	 * @param accountId
	 * @param clientId
	 * @param amount
	 */
	public Account(Long accountId, Long clientId, BigDecimal amount) {
		this.accountId = accountId;
		this.clientId = clientId;
		this.amount = amount;
	}
	
	/**
	 * @param clientId
	 * @param amount
	 */
	public Account(Long clientId, BigDecimal amount) {
		this.clientId = clientId;
		this.amount = amount;
	}
	
	/**
	 * @param amount
	 */
	public Account(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * 
	 */
	public Account() {}

	/**
	 * @return the accountId
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
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

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", clientId=" + clientId + ", amount=" + amount + "]";
	}

}
