package rs.digitize.backend.bean.filter;


import rs.digitize.backend.entity.Media;
import rs.digitize.backend.repository.MediaRepository;


public class MediaCountFilter extends CountFilter<Media> {

	public MediaCountFilter() {
		super(MediaRepository.class);
	}

	@Override
	public Long getCount() {
		return repository.count();
	}
}
