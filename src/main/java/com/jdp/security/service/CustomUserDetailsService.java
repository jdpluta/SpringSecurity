package com.jdp.security.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.jdp.security.entity.User;
import com.jdp.security.model.CustomUserDetails;
import com.jdp.security.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByName(username); 
		optionalUser
		.orElseThrow(() -> new UsernameNotFoundException("User name not Found"));

		return optionalUser.map(CustomUserDetails::new).get();
	}
}
