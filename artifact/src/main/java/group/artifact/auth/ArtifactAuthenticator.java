package group.artifact.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.artifact.model.User;
import group.artifact.service.i.IUserService;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import com.google.common.base.Optional;

@Service
public class ArtifactAuthenticator implements Authenticator<BasicCredentials, User> {
	
	@Autowired
	private IUserService userService;
	
	
	@Override
	public Optional<User> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
		return Optional.of(userService.findOne(1L));
	}
}
