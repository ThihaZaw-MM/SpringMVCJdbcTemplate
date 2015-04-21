package com.mahar.utilities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public abstract class DataAccess<T> implements IDataAccess<T> {
	private JdbcTemplate jdbcTemplate;
	public abstract boolean saveOrUpdate();
	public abstract boolean saveOrUpdateBatch(List<T> entities);
	public abstract boolean delete();
	public abstract T get();
	public abstract List<T> getList();
	protected abstract void updateRecord(T entity, PreparedStatement ps);
	protected abstract T readRecord(ResultSet rs);
	
	protected T entity;
	public T getEntity() { return this.entity; }
	public void setEntity(T entity) { this.entity = entity; }
	
	public DataAccess(DataSource dataSource){
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	protected boolean saveData(String query, Object[] parameters){
		//Object[] parameters = updateRecord(this.entity);
		int aff = jdbcTemplate.update(query,parameters);
		return (aff > 0) ? true : false;
	}
	 
	protected boolean updateBatch(final List<T> entities, String query){
		jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				T entity = entities.get(i);
				updateRecord(entity, ps);
			}
			
			@Override
			public int getBatchSize() {
				return entities.size();
			}
		});
		return false;
	}
	
	protected T getSingleRecord(String query){
		return jdbcTemplate.query(query, new ResultSetExtractor<T>(){
			@Override
			public T extractData(ResultSet rs) throws SQLException,
				DataAccessException {
				if(rs.next()){
					T entity = readRecord(rs);
					return entity;
				}
				return null;
			}
		});
	}
	
	protected List<T> getMultiRecords(String query){
		
		List<T> listContact = jdbcTemplate.query(query, new RowMapper<T>(){
			@Override 
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {
				T entity = readRecord(rs);
				return entity;
			}
		});
		return listContact;
	}
}
