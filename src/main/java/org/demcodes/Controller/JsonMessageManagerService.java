package org.demcodes.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.demcodes.Model.Employee;
import org.demcodes.Model.Message;
import org.demcodes.wrapper.DataWrapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonMessageManagerService {


    private ObjectMapper mapper = new ObjectMapper();
    private DataWrapper dw ;
    private File file = new File("employee.json");


    public    void  saveMessage(Message message){

        try{
            if ( file.exists()){
                dw = mapper.readValue(file , DataWrapper.class);
            } else dw = DataWrapper.getDataWrapperInstance();

            dw.getMessages().add(message);
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, dw);
            System.out.println("Le message  à été bien enregistrer avec Succes");

        }catch (IOException e){
            System.err.println("Erreur lors de \' enregistrement  du Message " + e.getMessage());
        }

    };


    public    void updateMessage(Message message){

    };

    public    void deleteMessage(Long messageId){};

    public    void getMessageById(Long messageId){

    };

    public List<Message> getAllMassage() throws  Exception{

        return  null;
    };
}
