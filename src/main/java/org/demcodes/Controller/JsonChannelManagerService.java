package org.demcodes.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.demcodes.Model.Channel;
import org.demcodes.Model.Employee;
import org.demcodes.wrapper.DataWrapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonChannelManagerService {


    private ObjectMapper mapper = new ObjectMapper();
    private DataWrapper dw ;
    private File file = new File("employee.json");


    public    void  saveChannel(Channel channel){

        try{
            if ( file.exists()){
                dw = mapper.readValue(file , DataWrapper.class);

            } else dw = DataWrapper.getInstance();
            dw.getChannels().add(channel);
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, dw);
            System.out.println("La chaine  à été bien enregistrer avec Succes");

        }catch (IOException e){
            System.err.println("Erreur lors de \' enregistrement  de la chaine" + e.getMessage());
        }

    };


    public    void updateChannel(String  channelId, Channel newChannel) throws Exception {
        List<Channel> channels =  new JsonChannelManagerService().getAllChannel();

        if(channels != null){
            for(Channel oldChannel : channels){
                if (oldChannel.getChannelId().equals(channelId)){
                    oldChannel = newChannel;

                }

            }
            try{
                if(file.exists()){
                    dw =  DataWrapper.getInstance();
                    dw.getChannels().addAll(channels);
                    mapper.writerWithDefaultPrettyPrinter().writeValue(file,dw);

                }
                else{
                    System.out.println("Le fichier n'existe pas lors de la modification de l\'employe");
                    dw =  DataWrapper.getInstance();
                }

            } catch (IOException e){
                System.out.println("Erreur lors de modification de l\'employee" + e.getMessage());
                dw =  DataWrapper.getInstance();
            }
        }

    };

    public    void deleteChannel(Long channelId){};

    public    void getChannelById(Long ChannelId){

    };

    public List<Channel> getAllChannel() throws  Exception{
        try {
            if (file.exists()) {
                dw = mapper.readValue(file, DataWrapper.class);
                DataWrapper.instance = dw;
            } else {
                System.out.println("Le fichier n'existe pas lors de la lecture des chaines.");
                dw =  DataWrapper.getInstance();
            }



        } catch (IOException e) {
            System.err.println("Erreur lors de la récupération des employés : " + e.getMessage());
            dw =  DataWrapper.getInstance();
        }

        return dw.getChannels();
    };
}
