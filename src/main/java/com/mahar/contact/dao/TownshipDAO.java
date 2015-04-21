package com.mahar.contact.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.mahar.contact.model.Township;
import com.mahar.utilities.DataAccess;

public class TownshipDAO extends DataAccess<Township> {

	public TownshipDAO(DataSource dataSource) {
		super(dataSource);
		super.setEntity(new Township());
	}

	@Override
	public boolean saveOrUpdate() {
		Township township = super.getEntity();
		//System.out.println("In TownshipDAO " + township.getId() + " " + township.getTownshipname());
		if(township.getId() > 0){
			String sql = "UPDATE township SET townshipname=?, division=? "
					+ "WHERE township_id=?";
			Object[] parameters = new Object[] {
					entity.getTownshipname(), entity.getDivision(), entity.getId()	
				};
			saveData(sql, parameters);
		}
		else {
			String sql = "INSERT INTO township (townshipname, division)"
					+ " VALUES (?, ?)";
			Object[] parameters = new Object[] {
					entity.getTownshipname(), entity.getDivision()};
			saveData(sql, parameters);
		}
		return true;
	}
	
	@Override
	public boolean saveOrUpdateBatch(List<Township> townshipList){
		
		return true;
	}

	@Override
	public boolean delete() {
		return false;
	}

	@Override
	public Township get() {
		String query = "SELECT * FROM township WHERE township_id = " + entity.getId();
		Township township = getSingleRecord(query);
		return township;
	}

	@Override
	public List<Township> getList() {
		String query = "SELECT * FROM township";
		List<Township> townshipList = getMultiRecords(query);
		return townshipList;
	}

	@Override
	protected Township readRecord(ResultSet rs) {
		Township township = new Township();
		try {
			township.setId(rs.getInt("township_id"));
			township.setTownshipname(rs.getString("townshipname"));
			township.setDivision(rs.getString("division"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return township;
	}

	@Override
	protected void updateRecord(Township township, PreparedStatement ps){
		try {
			ps.setString(1, township.getTownshipname());
			ps.setString(2, township.getDivision());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
