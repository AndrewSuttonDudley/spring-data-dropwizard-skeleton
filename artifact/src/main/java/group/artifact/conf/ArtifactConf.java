package group.artifact.conf;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;

@Configuration
@ComponentScan({
	"group.artifact.auth",
	"group.artifact.resource",
	"group.artifact.service"
})
public class ArtifactConf implements ApplicationContextAware {
	
	private static ApplicationContext ctx;
	
	private static Environment env;
	
	
	@Bean
	public static PropertyPlaceholderConfigurer propConfig() {
		PropertyPlaceholderConfigurer ppc =  new PropertyPlaceholderConfigurer();
		ppc.setLocation(new FileSystemResource("/etc/artifact/config.properties"));
		return ppc;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		ctx = context;
	}
	
	public static void autowireBean(Object object) {
		AutowireCapableBeanFactory factory = ctx.getAutowireCapableBeanFactory();
		factory.autowireBean(object);
	}
	
	public static String getProperty(String key) {
		return env.getProperty(key);
	}
}
