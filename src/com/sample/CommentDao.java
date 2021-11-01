package com.sample;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {

	// INSERT
	public static void insert(Comment com) throws SQLException {

		String url = "jdbc:mysql://localhost:3306/exercise";
		String user = "root";
		String password = "password";

		Connection con = DriverManager.getConnection(url, user, password);

		PreparedStatement ps = con.prepareStatement("insert into comment values(?, ?, ?, ?)");

		ps.setInt(1, com.getId());
		ps.setDate(2, new Date(com.getDate().getTime()));
		ps.setString(3, com.getName());
		ps.setString(4, com.getContent());

		ps.execute();

		con.close();

	}

	// SELECT（一覧取得）
	public static List<Comment> findAllComment() throws SQLException {

		String url = "jdbc:mysql://localhost:3306/exercise";
		String user = "root";
		String password = "password";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		Connection con = DriverManager.getConnection(url, user, password);

		PreparedStatement ps = con.prepareStatement("select * from comment");
		ResultSet rs = ps.executeQuery();

		List<Comment> list = new ArrayList<>();

		try {
			while (rs.next()) {
				Comment com = new Comment();
				com.setId(rs.getInt("id"));
				com.setDate(rs.getDate("date"));
				com.setName(rs.getString("name"));
				com.setContent(rs.getString("content"));
				list.add(com);
			}
		} finally {
			con.close();
		}

		return list;

	}

	// SELECT（最終ID取得）
	public static int findMaxId() throws SQLException {

		String url = "jdbc:mysql://localhost:3306/exercise";
		String user = "root";
		String password = "password";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		Connection con = DriverManager.getConnection(url, user, password);

		PreparedStatement ps = con.prepareStatement("select max(id) from comment");
		ResultSet rs = ps.executeQuery();

		int id = rs.getInt("id");

		con.close();

		return id;

	}

}
