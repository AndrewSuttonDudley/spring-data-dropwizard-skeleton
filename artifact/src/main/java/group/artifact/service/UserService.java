package group.artifact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import group.artifact.model.User;
import group.artifact.repository.UserRepository;
import group.artifact.service.i.IUserService;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}
	
	@Override
	public List<User> findAll() {
		return Lists.newArrayList(userRepository.findAll());
	}
	
	@Override
	public List<User> findAll(int page, int size) {
		return Lists.newArrayList(userRepository.findAll(new PageRequest(page, size)));
	}
	
	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
}
