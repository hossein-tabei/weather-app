package demo.application.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppConfig {

	private final Environment env;
	
	public String getConfigValue(String key) {
		return env.getProperty(key);
	}
}
