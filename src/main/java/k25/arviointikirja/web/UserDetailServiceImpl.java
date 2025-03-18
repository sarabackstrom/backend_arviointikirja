package k25.arviointikirja.web;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import k25.arviointikirja.domain.AppUser;
import k25.arviointikirja.domain.AppUserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	/*
	 * @Autowired
	 * AppUserRepository repository;
	 */

	private final AppUserRepository repository;

	public UserDetailServiceImpl(AppUserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser curruser = repository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(),
				AuthorityUtils.createAuthorityList(curruser.getRole()));
		return user;
	}
}
