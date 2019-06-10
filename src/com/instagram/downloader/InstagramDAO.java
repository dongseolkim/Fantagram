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
			JOptionPane.showMessageDialog(null, "���������� ���� �Ǿ����ϴ�.");
		} catch (Exception e) {
			System.out.println("[����] qryInsert() �޼ҵ��� SQL ����" + e.getMessage());
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
			JOptionPane.showMessageDialog(null, rows + "�� ����Ʈ�� ���������� ������Ʈ �Ǿ����ϴ�.");
		} catch (SQLException e) {
			System.out.println("[����] qryDataUpdate() �޼ҵ��� SQL ���� = " + e.getMessage());
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
			JOptionPane.showMessageDialog(null, rows + "�� ����Ʈ�� ���������� ���� �Ǿ����ϴ�.");
		} catch (SQLException e) {
			System.out.println("[����] qryDataDelete() �޼ҵ��� SQL ���� = " + e.getMessage());
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
			System.out.println("[����] qryDataSearch() �޼ҵ��� SQL ���� = " + e.getMessage());
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
			System.out.println("[����] qrySelectAll() �޼ҵ��� SQL ���� = " + e.getMessage());
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
			System.out.println("[����] qryVersion() �޼ҵ��� SQL ����" + e.getMessage());
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

			JOptionPane.showMessageDialog(null, "���������� ������Ʈ �Ǿ����ϴ�.\n���α׷��� �ٽ� �����Ͻñ� �ٶ��ϴ�.");
		} catch (SQLException e) {
			System.out.println("[����]update() �޼ҵ��� SQL ���� = " + e.getMessage());
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
			System.out.println("[����] qryVersion() �޼ҵ��� SQL ����" + e.getMessage());
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

			JOptionPane.showMessageDialog(null, "�����Ͱ� �ʱ�ȭ �Ǿ����ϴ�.\n���α׷��� �ٽ� ������ �ֽñ� �ٶ��ϴ�.");
		} catch (Exception e) {
			System.out.println("[����] qryResetTable() �޼ҵ��� SQL ����" + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
	}
}
