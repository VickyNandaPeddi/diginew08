package com.aashdit.digiverifier.login.model;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

import com.aashdit.digiverifier.config.admin.model.Role;
import com.aashdit.digiverifier.config.admin.model.User;

public class LoggedInUser extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 7767108758059803455L;

	private Role primaryRole;

	private User dbUser;

	public LoggedInUser(String username, String password, boolean enabled, boolean accountNonExpired,
				boolean credentialsNonExpired, boolean accountNonLocked,
				Collection<? extends GrantedAuthority> authorities,  Role primaryRole, User dbUser)
    {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

		
		this.primaryRole = primaryRole;
		this.dbUser = dbUser;
		

	}

	public Role getPrimaryRole() {
		return primaryRole;
	}
	
	public User getDbUser()
	{
		return dbUser;
	}
}
