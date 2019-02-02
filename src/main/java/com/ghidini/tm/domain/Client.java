package com.ghidini.tm.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CLIENT")
@SequenceGenerator(name = "SEQ_CLIENT", allocationSize = 1, sequenceName = "SEQ_CLIENT")
public class Client implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLIENT")
	@Column(name = "CLIENT_ID")
	private Long clientId;
	
	@Column(name = "CLIENT_NAME", nullable = false)
	private String clientName;
	
	/**
	 * @param clientId
	 * @param clientName
	 */
	public Client(Long clientId, String clientName) {
		this.clientId = clientId;
		this.clientName = clientName;
	}
	
	/**
	 * @param clientId
	 * @param clientName
	 */
	public Client(String clientName) {
		this.clientName = clientName;
	}
	
	/**
	 * 
	 */
	public Client() {}

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
	 * @return the clientName
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * @param clientName the clientName to set
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientName=" + clientName + "]";
	}
	
}
