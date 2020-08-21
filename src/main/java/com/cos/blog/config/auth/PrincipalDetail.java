package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Getter;

//스프링시큐리티가 로그인을 가로채서 로그인을 진행하고 완료가 되면 UserDetail 타입의 오브젝트를 
//스프링시큐리티의 고유한 세션저장소에 저장을 해준다 .
@Getter
public class PrincipalDetail implements UserDetails {

	private User user;
	
	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

//	계정이 만료되지 않았는지 리턴한다(true:만료않됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

//	계정이 잠겼는지 안았는지 리턴한다.(true:안잠김)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

//	비밀번호가 만료되지 않았는지 리턴한다.(true:만료않됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

//	계정이 활성화 되어있는지 리턴한다(true:활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
//	계정이 갖고있는 권한 목록을 리턴한다.(권한이 여러개 있을수 있어서 루프를 돌아야 하는데 여기는 한개만)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
//		collectors.add(new GrantedAuthority() {
//			
//			@Override
//			public String getAuthority() {
//				return "ROLE_"+user.getRole(); //ROLE_USER
//			}
//		});
		collectors.add(()->{return "ROLE_"+user.getRole();});
		return null;
	}
	
}
