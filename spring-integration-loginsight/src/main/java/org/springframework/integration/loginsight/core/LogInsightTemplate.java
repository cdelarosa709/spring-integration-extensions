package org.springframework.integration.loginsight.core;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang3.StringUtils;
import org.springframework.integration.loginsight.support.LogInsightServer;
import org.springframework.util.concurrent.CompletableToListenableFutureAdapter;
import org.springframework.util.concurrent.ListenableFuture;

import com.vmware.loginsightapi.AggregateQuery;
import com.vmware.loginsightapi.Configuration;
import com.vmware.loginsightapi.LogInsightClient;
import com.vmware.loginsightapi.MessageQuery;
import com.vmware.loginsightapi.core.AggregateResponse;
import com.vmware.loginsightapi.core.IngestionRequest;
import com.vmware.loginsightapi.core.IngestionResponse;
import com.vmware.loginsightapi.core.MessageQueryResponse;

public class LogInsightTemplate implements LogInsightOperations {

	private LogInsightClient client;
	private LogInsightServer server;
	private UUID agentId = UUID.randomUUID();

	public LogInsightTemplate() {

	}

	public LogInsightTemplate(LogInsightServer server) {
		this.setServer(server);
		Configuration config = new Configuration(server.getHost(), server.getUsername(), server.getPassword());
		System.out.println("Ingestin port " + server.getIngestionPort());
		if (0 != server.getIngestionPort()) {
			config.setIngestionPort(Integer.toString(server.getIngestionPort()));
		}
		if (0 != server.getPort()) {
			config.setPort(Integer.toString(server.getPort()));
		}
		if (null != server.getScheme()) {
			config.setScheme(server.getScheme());
		}
		try {
			if (null != server.getAgentId() && !StringUtils.isEmpty(server.getAgentId())) {
				UUID uuid = UUID.fromString(server.getAgentId());
				config.setAgentId(uuid.toString());
			} else {
				// set local agentId in the template bean.
				config.setAgentId(agentId.toString());
			}
		} catch (IllegalArgumentException ia) {
			// set local agentId in the template bean.
			config.setAgentId(agentId.toString());
		}
		this.client = new LogInsightClient(config);
	}

	@Override
	public ListenableFuture<IngestionResponse> send(IngestionRequest request) {
		CompletableFuture<IngestionResponse> future = client.ingest(request);
		return new CompletableToListenableFutureAdapter<IngestionResponse>(future);
	}

	@Override
	public ListenableFuture<MessageQueryResponse> eventQuery(MessageQuery mq) {
		CompletableFuture<MessageQueryResponse> future = client.messageQuery(mq.toUrlString());
		return new CompletableToListenableFutureAdapter<MessageQueryResponse>(future);
	}

	@Override
	public ListenableFuture<AggregateResponse> aggregateQuery(AggregateQuery aq) {
		CompletableFuture<AggregateResponse> future = client.aggregateQuery(aq.toUrlString());
		return new CompletableToListenableFutureAdapter<AggregateResponse>(future);
	}

	public LogInsightServer getServer() {
		return server;
	}

	public void setServer(LogInsightServer server) {
		this.server = server;
	}

}
