package com.sample.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

public interface CommentDao {

	@Insert("insert into comment values (#{id}, #{date}, #{name}, #{content})")
	public void insert(Comment comment);

	@ResultMap("commentResult")
	@Select("select * from comment")
	public List<Comment> findAllComment();

}
