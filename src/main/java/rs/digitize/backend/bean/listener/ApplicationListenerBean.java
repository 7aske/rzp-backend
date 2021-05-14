package rs.digitize.backend.bean.listener;

import rs.digitize.backend.bean.ProfileManager;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationListenerBean implements ApplicationListener<ContextRefreshedEvent> {
	private final ProfileManager profileManager;
	private final Logger logger = LoggerFactory.getLogger(ApplicationListenerBean.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.info("Application started in {} profiles", profileManager.getActiveProfiles());

		if (profileManager.isProfileActive("staging")) {
		}

		if (profileManager.isProfileActive("dev")) {
		}
	}
}
