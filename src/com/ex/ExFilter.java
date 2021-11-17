package com.ex;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ex.dao.Comment;
import com.ex.dao.CommentDao;
import com.ex.dao.Reply;
import com.ex.dao.ReplyDao;

/**
 * Servlet Filter implementation class WorkFilter
 */
// css効かなかった原因は@WebFilter("/*")と書いていたから（前はできてたのに）
// @WebFilter(urlPatterns={"URLパターン", "URLパターン"})こうすれば複数選択できるが
// やっぱり/commentは通らないのでCommentServletにreplyも取得する必要あり
@WebFilter("/comment.jsp")
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
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    chain.doFilter(request, response);

	    String resource = "config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		SqlSession session = sqlSessionFactory.openSession();

		// コメント全件取得
		CommentDao comDao = session.getMapper(CommentDao.class);
		List<Comment> list = comDao.findAllComment();

		// 返信全件表示
		ReplyDao repDao = session.getMapper(ReplyDao.class);
		List<Reply> reply = repDao.findAllReply();

		request.setAttribute("list", list);
		request.setAttribute("reply", reply);

		session.commit();
		session.close();

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
