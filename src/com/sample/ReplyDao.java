package com.sample;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReplyDao {

	// INSERT
	public static void insert(Reply rep) throws SQLException {

		String url = "jdbc:mysql://localhost:3306/exercise";
		String user = "root";
		String password = "password";

		Connection con = DriverManager.getConnection(url, user, password);

		PreparedStatement ps = con.prepareStatement("insert into reply values(?, ?, ?, ?, ?)");
		ps.setInt(1, rep.getRepId());
		ps.setDate(2, new Date(rep.getRepDate().getTime()));
		ps.setString(3, rep.getRepName());
		ps.setString(4, rep.getRepContent());
		ps.setInt(5, rep.getComId());

		ps.execute();

		con.close();

	}

	// SELECT（一覧表示）
	public static List<Reply> findAllReply(int comId) throws SQLException {

		String url = "jdbc:mysql://localhost:3306/exercise";
		String user = "root";
		String password = "password";

		Connection con = DriverManager.getConnection(url, user, password);

		PreparedStatement ps = con.prepareStatement("select * from reply where com_id = ?");
		ps.setInt(1, comId);
		ResultSet rs = ps.executeQuery();

		List<Reply> reply = new ArrayList<>();

		try {
			while (rs.next()) {
				Reply rep = new Reply();
				rep.setRepId(rs.getInt("repId"));
				rep.setRepDate(rs.getDate("repDate"));
				rep.setRepName(rs.getString("repName"));
				rep.setRepContent(rs.getString("repContent"));
				rep.setComId(rs.getInt("comId"));
				reply.add(rep);
			}
		} finally {
			con.close();
		}

		return reply;

	}

}
