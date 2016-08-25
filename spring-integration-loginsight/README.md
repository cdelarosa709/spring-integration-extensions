# Spring Integration LogInsight Adapter

## Features
* Send messages to loginsight
* Perform message queries/aggregate queries on loginsight
* Webhooks inbound adatper

## Inbound Channel Adapter

The Inbound channel adapter is used to read data from LogInsight and output a message containing the data to a Spring Integration channel. Inbound adapters can be used to perform 
### Blocking Event Search

```xml
<int-loginsight:inbound-channel-adapter id="logInsightInboundChannelAdapter"
        search="query-url-segment"
        template="loginsightTemplate"
        channel="inputFromLoginsight"
</int-loginsight:inbound-channel-adapter>
```

### Non Blocking Event search

```xml
<int-loginsight:inbound-channel-adapter id="logInsightInboundChannelAdapter"
        search="query-url-segment"
        template="loginsightTemplate"
        channel="inputFromLoginsight"
        handler=""
</int-loginsight:inbound-channel-adapter>
```

### Blocking Aggregate Event or Even Group Search

```xml
<int-loginsight:inbound-channel-adapter id="logInsightInboundChannelAdapter"
        search="query-url-segment"
        template="loginsightTemplate"
        channel="inputFromLoginsight"
</int-loginsight:inbound-channel-adapter>
```

### Non blocking aggregate event search

```xml
<int-loginsight:inbound-channel-adapter id="logInsightInboundChannelAdapter"
        search="query-url-segment"
        template="loginsightTemplate"
        channel="inputFromLoginsight"
</int-loginsight:inbound-channel-adapter>
```


## Outbound Channel Adapter

The Outbound channel adapter is used to ingest events into LogInsight and returns a response to the ingestion. Outbound channel adapters can be used to perform

### Blocking ingestion

```xml
<int-loginsight:outbound-channel-adapter id="logInsightOutboundChannelAdapter" channel="outputToLogInsight" template="loginsightTemplate"/>
```

### Non blocking ingestion

```xml
<int-loginsight:outbound-channel-adapter id="logInsightOutboundChannelAdapter" channel="outputToLogInsight" template="loginsightTemplate" handler="messageHandlerBean"/>
```


## Configuring server and template bean
It is mandatory to configure the server and template beans

```xml
<int-loginsight:server id="loginsightServer" username="admin" password="password" 
        timeout="5000" host="loginsight.vmware.com" 
        port="9000" ingestion-port="9583" agent-id=""/>
<int-loginsight:template id="loginsightTemplate" server-ref="loginsightServer"/>
```
