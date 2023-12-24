package demo.application.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
	
	@Autowired
	private Environment env;
	
	public String getConfigValue(String key) {
		return env.getProperty(key);
	}
}
