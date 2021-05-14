package rs.digitize.backend.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class ProfileManager {

	private Environment environment;

	@Autowired
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public Set<String> getActiveProfiles() {
		return new HashSet<>(Arrays.asList(environment.getActiveProfiles()));
	}

	public boolean isProfileActive(String profile) {
		return getActiveProfiles().contains(profile);
	}

	public boolean isAnyProfileActive(String... profiles) {
		return getActiveProfiles()
				.stream()
				.anyMatch(profile -> Arrays.asList(profiles).contains(profile));
	}
}
