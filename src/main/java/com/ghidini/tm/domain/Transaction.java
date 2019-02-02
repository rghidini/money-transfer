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
@Table(name="TRANSACTION")
public class Transaction implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "SEQ_TRANSACTION", allocationSize = 1, sequenceName = "SEQ_TRANSACTION")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRANSACTION")
	@Column(name = "TRANSACTION_ID")
	private Long transactionId;
	
	@Column(name = "FROM_ACCOUNT", nullable = false)
	private Long fromAccountId;
	
	@Column(name = "TO_ACCOUNT", nullable = false)
	private Long toAccountId;
	
	@Column(name = "AMOUNT", nullable = false)
	private BigDecimal amount;
	
	/**
	 * @param transactionId
	 * @param fromAccountId
	 * @param toAccountId
	 * @param amount
	 */
	public Transaction(Long transactionId, Long fromAccountId, Long toAccountId, BigDecimal amount) {
		this.transactionId = transactionId;
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.amount = amount;
	}
	
	/**
	 * @param fromAccountId
	 * @param toAccountId
	 * @param amount
	 */
	public Transaction(Long fromAccountId, Long toAccountId, BigDecimal amount) {
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.amount = amount;
	}
	

	/**
	 * 
	 */
	public Transaction() {}

	/**
	 * @return the transactionId
	 */
	public Long getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", fromAccountId=" + fromAccountId + ", toAccountId="
				+ toAccountId + ", amount=" + amount + "]";
	}
	
}
