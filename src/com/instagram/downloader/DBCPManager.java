package com.instagram.downloader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

//상속 전용 클래스(추상클래스)
public abstract class DBCPManager {
	
	private static PoolDataSource _pds;

	// 한번 실행됨
	static {
		_pds=PoolDataSourceFactory.getPoolDataSource();
		
		try {
			_pds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
			_pds.setURL("jdbc:oracle:thin:@192.168.14.27:1521:xe");
			_pds.setUser("scott");
			_pds.setPassword("tiger");
			_pds.setInitialPoolSize(3);
			_pds.setMaxPoolSize(5);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		Connection con = null;
		try {
			con = _pds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public void close(Connection con) {
		try {
			if(con!=null) con.close();
		} catch (Exception e) {}
	}

	public void close(Connection con, PreparedStatement pstmt) {
		try {
			if(con!=null) con.close();
			if(pstmt!=null) pstmt.close();
		} catch (Exception e) {}
	}
	
	
	public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		} catch (Exception e) {}
	}
}
