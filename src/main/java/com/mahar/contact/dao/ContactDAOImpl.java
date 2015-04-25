package com.mahar.contact.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.mahar.contact.model.Contact;
import com.mahar.utilities.AppUtility;

/**
 * An implementation of the ContactDAO interface.
 * @author Thiha Zaw
 *
 */

public class ContactDAOImpl implements ContactDAO {

	private JdbcTemplate jdbcTemplate;
	
	public ContactDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdate(Contact contact) {
		//System.out.println("Reached to saveOrUpdate with " + contact.getName() + "-" + contact.getDateofbirth() );
		if (contact.getId() > 0) {
			// update
			String sql = "UPDATE contact SET name=?, dateofbirth=?, email=?, address=?, "
						+ "telephone=? WHERE contact_id=?";
			java.sql.Timestamp dob = AppUtility.formatSqlDate(contact.getdateofbirth());
			System.out.println("In saveOrUpdate Method " + dob.toString());
			jdbcTemplate.update(sql, new Object[] {contact.getName(), dob, contact.getEmail(),
					contact.getAddress(), contact.getTelephone(), contact.getId()});
		} else {
			// insert
			String sql = "INSERT INTO contact (name, dateofbirth, email, address, telephone)"
						+ " VALUES (?, ?, ?, ?, ?)";
			java.sql.Timestamp dob = AppUtility.formatSqlDate(contact.getdateofbirth());
			jdbcTemplate.update(sql, contact.getName(), dob, contact.getEmail(),
					contact.getAddress(), contact.getTelephone());
			
		}
	}

	@Override
	public void delete(int contactId) {
		// implementation details goes here...
		String sql = "DELETE FROM contact WHERE contact_id=?";
		jdbcTemplate.update(sql, contactId);
	}

	@Override
	public List<Contact> list() {
		// implementation details goes here...
		String sql = "SELECT * FROM contact";
		List<Contact> listContact = jdbcTemplate.query(sql, new RowMapper<Contact>(){
			@Override 
			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				Contact aContact = new Contact();

				aContact.setId(rs.getInt("contact_id"));
				aContact.setName(rs.getString("name"));
				String dob = AppUtility.formatDate(rs.getString("dateofbirth"));
				//System.out.println(dob);
				aContact.setdateofbirth(dob);
				//System.out.println(aContact.getDateOfBirth());
				aContact.setEmail(rs.getString("email"));
				aContact.setAddress(rs.getString("address"));
				aContact.setTelephone(rs.getString("telephone"));

				return aContact;
			}
		});
		return listContact;
	}
	
	@Override
	public List<Contact> list(Integer pageNumber, Integer limit) {
		// implementation details goes here...
		int start = ((pageNumber-1)*limit);
		
		String sql = "SELECT * FROM contact ORDER BY contact_id LIMIT " + start + "," + limit;
		//System.out.println(sql);
		List<Contact> listContact = jdbcTemplate.query(sql, new RowMapper<Contact>(){
			@Override 
			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				Contact aContact = new Contact();
				int contactid = rs.getInt("contact_id");
				aContact.setId(contactid);
				aContact.setName(rs.getString("name"));
				String dob = AppUtility.formatDate(rs.getString("dateofbirth"));
				//System.out.println(dob);
				aContact.setdateofbirth(dob);
				//System.out.println(aContact.getDateOfBirth());
				aContact.setEmail(rs.getString("email"));
				aContact.setAddress(rs.getString("address"));
				aContact.setTelephone(rs.getString("telephone"));

				String editLink = "<a href=\"/contact/editContact?id=" + contactid + "\">Edit</a>";
				String deleteLink = "<a href=\"/contact/deleteContact?id=" + contactid + "\">Delete</a>";
				aContact.setActionlink(editLink + "&nbsp;&nbsp;&nbsp;&nbsp;" + deleteLink);
				System.out.println(editLink);
				return aContact;
			}
		});
		return listContact;
	}
	

	@Override
	public Contact get(int contactId) {
		// implementation details goes here...
		String sql = "SELECT * FROM contact WHERE contact_id=" + contactId;
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Contact>(){
			@Override
			public Contact extractData(ResultSet rs) throws SQLException,
				DataAccessException {
				if(rs.next()){
					Contact contact = new Contact();
					contact.setId(rs.getInt("contact_id"));
					contact.setName(rs.getString("name"));
					String dob = AppUtility.formatDate(rs.getString("dateofbirth"));
					//System.out.println(dob);
					contact.setdateofbirth( dob);
					contact.setEmail(rs.getString("email"));
					contact.setAddress(rs.getString("address"));
					contact.setTelephone(rs.getString("telephone"));
					return contact;
				}
				return null;
			}
		});
	}

	@Override
	public int getCount() {
		String sql = "SELECT COUNT(*) FROM contact";
		int count = (Integer) jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}
}
