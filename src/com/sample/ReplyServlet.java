package com.sample;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReplyServlet
 */
@WebServlet("/reply")
public class ReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ReplyDao dao = new ReplyDao();
		Reply rep = new Reply();

		int comId = Integer.parseInt(request.getParameter("comId"));

		List<Reply> reply = new ArrayList<>();
		try {
			reply = dao.findAllReply(comId);
			// もしリストがなかったらIDに1を入れる
			if (reply.size() == 0) {
				rep.setRepId(1);
			} else {
				// リストがあれば、最後のIDを取得する
				int id = reply.get(reply.size() - 1).getRepId();
				// +1したIDをセットする
				rep.setRepId(id + 1);
			}
//			int repId = 0;
//			for (Reply r : reply) {
//				if (r.getComId() == comId) {
//					repId++;
//				}
//			}
			// rep.setRepId(repId);
		} catch (SQLException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		rep.setRepDate(new Date());
		rep.setRepName(request.getParameter("repName"));
		rep.setRepContent(request.getParameter("repContent"));
		rep.setComId(comId);

		try {
			dao.insert(rep);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		reply.add(rep);
		request.setAttribute("reply", reply);

		RequestDispatcher rd = request.getRequestDispatcher("/comment.jsp");
		rd.forward(request, response);
		return;
	}

}
