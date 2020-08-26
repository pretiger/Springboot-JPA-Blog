package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.Reply2;

public interface Reply2Repository extends JpaRepository<Reply2, Integer>{

	@Modifying
	@Query(value="insert into reply2(userId, boardId, content, createDate) values (?1, ?2, ?3, now())", nativeQuery = true)
	int mSave(int userId, int boardId, String content);
}
