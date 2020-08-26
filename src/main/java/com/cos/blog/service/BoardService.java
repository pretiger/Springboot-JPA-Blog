package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.Reply2SaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.Reply2Repository;
import com.cos.blog.repository.UserRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private Reply2Repository reply2Repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void 댓글삭제(int reply2Id) {
		reply2Repository.deleteById(reply2Id);
	}
	
	@Transactional
	public void 댓글쓰기(Reply2SaveRequestDto reply2SaveRequestDto) {
//		User user = userRepository.findById(reply2SaveRequestDto.getUserId()).orElseThrow(()->{
//			return new IllegalArgumentException("댓글 쓰기 실패 : 유저 id를 찾을 수 없습니다.");
//		});
//		Board board = boardRepository.findById(reply2SaveRequestDto.getBoardId()).orElseThrow(()->{
//			return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 id를 찾을 수 없습니다.");
//		});
//		
//		Reply2 reply2 = Reply2.builder()
//				.user(user)
//				.board(board)
//				.content(reply2SaveRequestDto.getContent())
//				.build();
//		
//		reply2Repository.save(reply2);
		reply2Repository.mSave(reply2SaveRequestDto.getUserId(), reply2SaveRequestDto.getBoardId(), reply2SaveRequestDto.getContent());
	}
	
	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없음.");
				});
	}
	
	@Transactional
	public void 글수정하기(int id, Board requestBoard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
				}); // 영속화 완료
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		//해당 함수로 종료시(Service가 종료될때) 트랜잭션이 종료됩니다. 이때 더티체킹 - 자동 업데이트가 됨. db flush
	}
	
	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	@Transactional
	public void 글쓰기(Board board, User user) {
		board.setUser(user);
		board.setCount(0);
		boardRepository.save(board);
	}

}
