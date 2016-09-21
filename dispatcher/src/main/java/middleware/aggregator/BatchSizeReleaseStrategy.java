package middleware.aggregator;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.integration.aggregator.ReleaseStrategy;
import org.springframework.integration.store.MessageGroup;

@ConfigurationProperties(prefix = "aggregator")
public class BatchSizeReleaseStrategy implements ReleaseStrategy {
	
	private int batchSize;

	@Override
	public boolean canRelease(MessageGroup group) {
		return group.getMessages().size()==batchSize;
	}

}
