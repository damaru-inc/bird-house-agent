package com.brewingmadscientists.birdhouseagent.streams.sources;

import com.brewingmadscientists.birdhouseagent.dto.BirdHouseAgentHeartbeatDTO;
import com.brewingmadscientists.birdhouseagent.streams.BirdHouseChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(BirdHouseChannel.class)
public class HeartbeatSource {
    private static final Logger log = LoggerFactory.getLogger(HeartbeatSource.class);

    private static final int agentIdentifier = 1;

    //Sends an Heartbeat every 5 seconds
    @InboundChannelAdapter(channel = BirdHouseChannel.HEARTBEAT, poller = @Poller(fixedRate = "5000"))
    public BirdHouseAgentHeartbeatDTO sendHeartbeat() {
        BirdHouseAgentHeartbeatDTO heartbeat = new BirdHouseAgentHeartbeatDTO(agentIdentifier);

        log.info("Emitting heartbeat " + heartbeat.getTimestamp().toString());

        return heartbeat;
    }

}
