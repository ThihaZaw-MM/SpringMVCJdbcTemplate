package com.mahar.contact.model;

import java.util.List;

public class ContactJsonObject {
	int iTotalRecords;
	 
    int iTotalDisplayRecords;
 
    String sEcho;
 
    String sColumns;
    
    List<Contact> aaData;
    
    /**
	 * @return the iTotalRecords
	 */
	public int getiTotalRecords() {
		return iTotalRecords;
	}

	/**
	 * @param iTotalRecords the iTotalRecords to set
	 */
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	/**
	 * @return the iTotalDisplayRecords
	 */
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	/**
	 * @param iTotalDisplayRecords the iTotalDisplayRecords to set
	 */
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	/**
	 * @return the sEcho
	 */
	public String getsEcho() {
		return sEcho;
	}

	/**
	 * @param sEcho the sEcho to set
	 */
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	/**
	 * @return the sColumns
	 */
	public String getsColumns() {
		return sColumns;
	}

	/**
	 * @param sColumns the sColumns to set
	 */
	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}

	/**
	 * @return the aaData
	 */
	public List<Contact> getAaData() {
		return aaData;
	}

	/**
	 * @param aaData the aaData to set
	 */
	public void setAaData(List<Contact> aaData) {
		this.aaData = aaData;
	}
}
