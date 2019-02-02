package com.ghidini.tm.domain.dto;

import java.io.Serializable;

public class ClientDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	
	private String clientName;
	
	/**
	 * @param clientName
	 */
	public ClientDTO(String clientName) {
		this.clientName = clientName;
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
		return "ClientDTO [clientName=" + clientName + "]";
	}
	
	



}
