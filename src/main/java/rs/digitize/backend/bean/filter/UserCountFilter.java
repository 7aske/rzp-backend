package rs.digitize.backend.bean.filter;


import rs.digitize.backend.entity.User;
import rs.digitize.backend.repository.UserRepository;

public class UserCountFilter extends CountFilter<User> {

	public UserCountFilter() {
		super(UserRepository.class);
	}

	@Override
	public Long getCount() {
		return repository.count();
	}
}
