package org.springframework.integration.loginsight.core;

import org.springframework.util.concurrent.ListenableFuture;

import com.vmware.loginsightapi.AggregateQuery;
import com.vmware.loginsightapi.MessageQuery;
import com.vmware.loginsightapi.core.AggregateResponse;
import com.vmware.loginsightapi.core.IngestionRequest;
import com.vmware.loginsightapi.core.IngestionResponse;
import com.vmware.loginsightapi.core.MessageQueryResponse;

public interface LogInsightOperations {
	
	public ListenableFuture<IngestionResponse> send(IngestionRequest request );
	public ListenableFuture<MessageQueryResponse> eventQuery(MessageQuery mq);
	public ListenableFuture<AggregateResponse> aggregateQuery(AggregateQuery aq);
	
}
