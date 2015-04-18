package com.mahar.contact.dao;

import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import com.mahar.contact.model.Township;
import com.mahar.utilities.DataAccess;

public class TownshipDAO extends DataAccess<Township> {

	public TownshipDAO(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
		super.setEntity(new Township());
	}

	@Override
	public boolean saveOrUpdate() {
		// TODO Auto-generated method stub
		Township township = super.getEntity();
		if(township.getId() > 0){
			String sql = "UPDATE township SET townshipname=?, division=? "
					+ "WHERE township_id=?";
			saveData(sql);
		}
		else {
			String sql = "INSERT INTO township (townshipname, division)"
					+ " VALUES (?, ?)";
			saveData(sql);
		}
		return true;
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Township get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Township> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object[] updateRecord(Township entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Township readRecord(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
