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

		String url = "jdbc:mysql://localhost:3306/grape";
		String user = "root";
		String password = "password";

		Connection con = DriverManager.getConnection(url, user, password);

		PreparedStatement ps = con.prepareStatement("insert into employee values(last_insert_id() + 1, ?, ?, ?)");
//		ResultSet rs = ps.getGeneratedKeys();
//		int autoId = 0;
//		if(rs.next()){
//            autoId = rs.getInt(1);
//        }
		// ps.setInt(1, rs.getInt(autoId));
		ps.setDate(1, new Date(com.getDate().getTime()));
		ps.setString(2, com.getName());
		ps.setString(3, com.getContent());

		ps.execute();

		con.close();

	}

	// SELECT（一覧取得）
	public static List<Comment> findAllComment() throws SQLException {

		String url = "jdbc:mysql://localhost:3306/grape";
		String user = "root";
		String password = "password";

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

}
