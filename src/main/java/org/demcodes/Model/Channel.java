package org.demcodes.Model;

import java.util.ArrayList;
import java.util.List;

public class Channel {

    public  Channel(){};

    public Channel(String channelId, String channelName) {
        this.channelId = channelId;
        this.channelName = channelName;

    }

    @Override
    public String toString() {
        return "Channel{" +
                "channelId='" + channelId + '\'' +
                ", channelName='" + channelName + '\'' +
                ", ChannelSuscriber=" + ChannelSuscriber +
                '}';
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public List<String> getChannelSuscriber() {
        return ChannelSuscriber;
    }

    public void setChannelSuscriber(List<String> channelSuscriber) {
        ChannelSuscriber = channelSuscriber;
    }

    String channelId;
    String channelName;
    List<String> ChannelSuscriber = new ArrayList<>();
}
