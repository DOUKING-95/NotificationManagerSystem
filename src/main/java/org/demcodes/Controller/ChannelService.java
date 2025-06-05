package org.demcodes.Controller;

import org.demcodes.Model.Channel;
import org.demcodes.Model.Employee;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ChannelService {

    private JsonChannelManagerService channelJsonManager;
    private  Channel channel;

    public  ChannelService(JsonChannelManagerService channelJsonManager, Channel channel){
        this.channelJsonManager = channelJsonManager;
        this.channel = channel;

    }

    public  static Channel initEmployee(){
        Scanner sc = new Scanner(System.in);
        String channelId = UUID.randomUUID().toString();
        System.out.println("Donner le nom de votre Chaine :");
        String channelName = sc.nextLine();
        return  new Channel( channelId,channelName);
    }


    public void   defaultChannel(){
        Channel channel1 = new Channel(UUID.randomUUID().toString(), "DevWeb");
        Channel channel2 = new Channel(UUID.randomUUID().toString(), "DevMobile");
        Channel channel3 = new Channel(UUID.randomUUID().toString(), "Design");
        List<Channel> channels = List.of(channel1, channel2, channel3);
        for(Channel channelData : channels){
            new JsonChannelManagerService().saveChannel(channelData);
        }
        return ;

    }

    public   void  addToChannelMembers(String channelId, String employeeId) throws Exception {
        List<Channel> channels =  new JsonChannelManagerService().getAllChannel();

        if(channels != null){
            for(Channel channel1 : channels){
                if (channel1.getChannelId().equals(channelId)){
                    channel1.getChannelSuscriber().add(employeeId);
                }
                new JsonChannelManagerService().updateChannel(channel1.getChannelId(),channel1);

            }
        }

    }

    public  static  void removeToChannelMembers(String channelId, String employeeId){

    }
}
