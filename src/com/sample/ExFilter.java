package com.sample;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.sample.dao.Comment;
import com.sample.dao.CommentDao;
import com.sample.dao.Reply;
import com.sample.dao.ReplyDao;

/**
 * Servlet Filter implementation class WorkFilter
 */
@WebFilter("/*")
public class ExFilter implements Filter {

    /**
     * Default constructor.
     */
    public ExFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 受信データと送信データの文字コードを指定して
	    request.setCharacterEncoding("UTF-8");
	    // 以下を設定しているとcssが効かなくなる
	    // response.setContentType("text/html; charset=UTF-8");
	    // 以下に変更する。もしくはなくても日本語表示されcssも効く(謎)
	    response.setCharacterEncoding("UTF-8");
	    // ここでそれらを繋げる
	    chain.doFilter(request, response);

	    // コメント全件取得
	    CommentDao comDao = new CommentDao();
		List<Comment> list = new ArrayList<>();

		// 返信全件表示
		ReplyDao repDao = new ReplyDao();
		List<Reply> reply = new ArrayList<>();

		try {
			list = comDao.findAllComment();
			reply = repDao.findAllReply();
		} catch (SQLException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		request.setAttribute("list", list);
		request.setAttribute("reply", reply);

		RequestDispatcher rd = request.getRequestDispatcher("/comment.jsp");
		rd.forward(request, response);
		return;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
