package org.demcodes.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.demcodes.Model.Channel;
import org.demcodes.Model.Employee;
import org.demcodes.wrapper.DataWrapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonChannelManagerService {


    private ObjectMapper mapper = new ObjectMapper();
    private DataWrapper dw ;
    private File file = new File("employee.json");


    public    void  saveChannel(Channel channel){

        try{
            if ( file.exists()){
                dw = mapper.readValue(file , DataWrapper.class);

            } else dw = DataWrapper.getDataWrapperInstance();
            dw.getChannels().add(channel);
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, dw);
            System.out.println("La chaine  à été bien enregistrer avec Succes");

        }catch (IOException e){
            System.err.println("Erreur lors de \' enregistrement  de la chaine" + e.getMessage());
        }

    };


    public    void updateChannel(Channel channel){

    };

    public    void deleteChannel(Long channelId){};

    public    void getChannelById(Long ChannelId){

    };

    public List<Employee> getAllChannel() throws  Exception{
return null;
    };
}
