package rs.digitize.backend.bean.filter;


import rs.digitize.backend.entity.Post;
import rs.digitize.backend.repository.PostRepository;


public class PostCountFilter extends CountFilter<Post> {

	public PostCountFilter() {
		super(PostRepository.class);
	}

	@Override
	public Long getCount() {
		return repository.count();
	}
}
