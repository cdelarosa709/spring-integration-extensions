# Spring Integration LogInsight Adapter

## Features
* Send messages to loginsight
* Perform message queries/aggregate queries on loginsight
* Webhooks inbound adatper

```xml
<int-loginsight:server id="loginsightServer" username="admin" password="password" timeout="5000" host="loginsight.vmware.com" port="9000" ingestion-port="9583" agent-id=""/>

<int-loginsight:outbound-channel-adapter id="logInsightOutboundChannelAdapter" channel="outputToLogInsight" loginsight-server-ref="loginsightServer"/>

<int-loginsight:inbound-channel-adapter id="logInsightInboundChannelAdapter"
        search="search spring:example"
        loginsight-server-ref="loginsightServer"
        channel="inputFromLoginsight" mode="BLOCKING" earliestTime="-1d" latestTime="now" initEarliestTime="-1d">
        <int:poller fixed-rate="5" time-unit="SECONDS"/>
</int-loginsight:inbound-channel-adapter>
    
```


