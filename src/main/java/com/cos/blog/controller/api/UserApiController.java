package com.cos.blog.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserApiController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user) {
		logger.info("======user정보========:"+user);
		userService.회원수정(user);
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
		
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		logger.info("UserApiController : save 호출됨");
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
//	@PostMapping("/api/login")
//	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//		logger.info("UserApiController : login 호출됨");
//		User principal = userService.로그인(user);
//		if(principal != null) {
//			session.setAttribute("principal", principal);
//		}
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}
	
//	@	GetMapping("/logout")
//	public ResponseDto<Integer> logout(HttpSession session) {
//		logger.info("UserApiController : logout 호출됨");
//		session.invalidate();
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}
}
