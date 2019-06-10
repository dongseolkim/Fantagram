package com.instagram.downloader;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class InstagramDAO extends DBCPManager {
	private static InstagramDAO _dao;

	private InstagramDAO() {
		// TODO Auto-generated constructor stub
	}

	public static InstagramDAO getInstagramDAO() {
		if (_dao == null) {
			_dao = new InstagramDAO();
		}
		return _dao;
	}

	public int qryDataInsert(InstagramDTO instagram) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;

		try {
			con = getConnection();
			String query = "insert into instagram values(?,?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, instagram.getNo());
			pstmt.setString(2, instagram.getName());
			pstmt.setString(3, instagram.getLink());
			pstmt.setString(4, instagram.getType());
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "정상적으로 저장 되었습니다.");
		} catch (Exception e) {
			System.out.println("[에러] qryInsert() 메소드의 SQL 오류" + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}

	public int qryDataUpdate(InstagramDTO instagram) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();

			String sql = "update instagram set name=?,link=? where no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, instagram.getName());
			pstmt.setString(2, instagram.getLink());
			pstmt.setInt(3, instagram.getNo());

			rows = pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, rows + "개 리스트가 정상적으로 업데이트 되었습니다.");
		} catch (SQLException e) {
			System.out.println("[에러] qryDataUpdate() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}

	public int qryDataDelete(InstagramDTO instagram) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();

			String query = "delete from instagram where no=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, instagram.getNo());
			rows = pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, rows + "개 리스트가 정상적으로 삭제 되었습니다.");
		} catch (SQLException e) {
			System.out.println("[에러] qryDataDelete() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}

	public List<InstagramDTO> qryDataSearch(String name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<InstagramDTO> instagramList = new ArrayList<InstagramDTO>();
		try {
			con = getConnection();
			String query = "select * from instagram where name=? order by no";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				InstagramDTO instagramDTO = new InstagramDTO();
				instagramDTO.setNo(rs.getInt("no"));
				instagramDTO.setName(rs.getString("name"));
				instagramDTO.setLink(rs.getString("Link"));
				instagramDTO.setType(rs.getString("type"));

				instagramList.add(instagramDTO);
			}
		} catch (SQLException e) {
			System.out.println("[에러] qryDataSearch() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return instagramList;
	}

	public List<InstagramDTO> qryAllSelect() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<InstagramDTO> instagramList = new ArrayList<InstagramDTO>();
		try {
			con = getConnection();
			String query = "select * from instagram order by no";
			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				InstagramDTO instagramDTO = new InstagramDTO();
				instagramDTO.setNo(rs.getInt("no"));
				instagramDTO.setName(rs.getString("name"));
				instagramDTO.setLink(rs.getString("link"));
				instagramDTO.setType(rs.getString("type"));

				instagramList.add(instagramDTO);
			}
		} catch (SQLException e) {
			System.out.println("[에러] qrySelectAll() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return instagramList;
	}

	public InstagramDTO qryVersionSelect() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		InstagramDTO instagram = null;

		try {
			con = getConnection();
			String query = "select * from instagram_version";
			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String version = rs.getString("version");
				Date released = rs.getDate("released");
				JOptionPane.showMessageDialog(null, "Version: " + version + "\nRelesed date: " + released);
				System.out.println("version = " + version + ", released = " + rs.getString("released"));
			}
		} catch (Exception e) {
			System.out.println("[에러] qryVersion() 메소드의 SQL 오류" + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return instagram;
	}

	public int qryPathUpdate(InstagramDTO instagram) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();

			String query = "update instagram_path set path=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, instagram.getPath());
			rows = pstmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "정상적으로 업데이트 되었습니다.\n프로그램을 다시 실행하시기 바랍니다.");
		} catch (SQLException e) {
			System.out.println("[에러]update() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}

	public InstagramDTO qryPathSelect() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		InstagramDTO instagram = null;

		try {
			con = getConnection();
			String query = "select * from instagram_path";
			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
//				instagram = new InstagramDTO();
//				instagram.setPath(rs.getString("path"));
				InstagramDownloaderApp.downloadPath = rs.getString("path");
//				JOptionPane.showMessageDialog(null, path);
				System.out.println("path: " + rs.getString("path"));
			}
		} catch (Exception e) {
			System.out.println("[에러] qryVersion() 메소드의 SQL 오류" + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return instagram;
	}

	public void qryResetTable() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			String query = "delete from instagram";
			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();

			JOptionPane.showMessageDialog(null, "데이터가 초기화 되었습니다.\n프로그램을 다시 실행해 주시기 바랍니다.");
		} catch (Exception e) {
			System.out.println("[에러] qryResetTable() 메소드의 SQL 오류" + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
	}
}
