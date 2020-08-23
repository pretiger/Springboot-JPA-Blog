package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void 회원수정(User user) {
//		수정시에는 영속성컨택스트 User 오브젝트를 영속화시키고 , 영속화된 User 오브젝트를 수정
//		select를 해서 User 오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해서
//		영속화된 오브젝트를 변경하면 자동으로 DB에 더티체킹을 통해 update문을 날려줌
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원찾기 실패");
		});
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
	}
	
	@Transactional
	public void 회원가입(User user) {
		String encPassword = encoder.encode(user.getPassword());
		user.setPassword(encPassword);
		userRepository.save(user);
	}
	
//	@Transactional(readOnly = true)
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
