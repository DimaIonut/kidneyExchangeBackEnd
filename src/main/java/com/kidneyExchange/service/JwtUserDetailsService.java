package com.kidneyExchange.service;

import java.util.ArrayList;

import com.kidneyExchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.kidneyExchange.config.WebSecurityConfig;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	WebSecurityConfig webSecurityConfig;

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		com.kidneyExchange.Entity.User user = userRepository.findByName(username).orElse(null);

		if (user != null) {
			return new User(user.getName(), webSecurityConfig.endocodePassword(user.getPassword()),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}