package com.mahar.contact.dao;

import java.util.List;

import com.mahar.contact.model.Contact;

/**
 * Defines DAO operations for the contact model.
 * @author Thiha Zaw
 *
 */
public interface ContactDAO {
	
	public void saveOrUpdate(Contact contact);
	
	public void delete(int contactId);
	
	public Contact get(int contactId);
	
	public List<Contact> list();
	
	public List<Contact> list(Integer pageNumber, Integer limit);

	public int getCount();
}
