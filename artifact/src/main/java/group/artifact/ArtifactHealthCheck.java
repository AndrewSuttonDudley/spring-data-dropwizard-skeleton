package group.artifact;

import com.codahale.metrics.health.HealthCheck;

public class ArtifactHealthCheck extends HealthCheck {
	
	@Override
	protected Result check() throws Exception {
		// TODO: figure out what's really supposed to be here
		return Result.healthy();
	}
}
