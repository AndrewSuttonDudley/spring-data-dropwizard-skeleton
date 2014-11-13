package group.artifact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import group.artifact.auth.ArtifactAuthenticator;
import group.artifact.conf.ArtifactConf;
import group.artifact.conf.MySQLPersistenceConfig;
import group.artifact.model.User;
import group.artifact.resource.UserResource;
import io.dropwizard.Application;
import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ArtifactApplication extends Application<ArtifactConfiguration> {
	
	private static final Logger logger = LoggerFactory.getLogger(ArtifactApplication.class);
	
	private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
	
	private ArtifactAuthenticator artifactAuthenticator;
	
	private UserResource userResource;
	
	
	public static void main(String[] args) throws Exception {
		new ArtifactApplication().run(args);
	}
	
	@Override
	public void initialize(Bootstrap<ArtifactConfiguration> arg0) {
		logger.info("Initializing.");
		
		ctx.register(ArtifactConf.class, MySQLPersistenceConfig.class);
		ctx.refresh();
		
		artifactAuthenticator = ctx.getBean(ArtifactAuthenticator.class);
		
		userResource = ctx.getBean(UserResource.class);
		
		logger.info("Initialization complete.");
	}
	
	@Override
	public void run(ArtifactConfiguration configuration, Environment environment) throws Exception {
		
		environment.jersey().register(new BasicAuthProvider<User>(artifactAuthenticator, "IDONTKNOWWHATTHISISYET"));
		
		environment.jersey().register(userResource);
		
		final ArtifactHealthCheck artifactHealthCheck = new ArtifactHealthCheck();
		environment.healthChecks().register("artifact", artifactHealthCheck);
	}
}
