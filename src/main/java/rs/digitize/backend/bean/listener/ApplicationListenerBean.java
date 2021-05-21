package rs.digitize.backend.bean.listener;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import rs.digitize.backend.bean.ProfileManager;
import rs.digitize.backend.util.IOUtils;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class ApplicationListenerBean implements ApplicationListener<ContextRefreshedEvent> {
	private final ProfileManager profileManager;
	private final Logger logger = LoggerFactory.getLogger(ApplicationListenerBean.class);
	@Value("${media.upload.path}")
	private String mediaUploadPath;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.info("Application started in {} profiles", profileManager.getActiveProfiles());

		if (profileManager.isProfileActive("staging")) {
		}

		if (profileManager.isProfileActive("dev")) {
		}

		Path mediaPath = Paths.get(mediaUploadPath, "static/media");
		logger.info("Creating static directory {}", mediaPath);
		IOUtils.createDirectoriesNoExcept(mediaPath);
	}
}
