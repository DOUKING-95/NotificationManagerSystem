package org.demcodes.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.demcodes.Model.Employee;
import org.demcodes.Model.Message;
import org.demcodes.wrapper.DataWrapper;

import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * Classe JsonMessageManagerService qui gère toute les requêtes de la base de données
 * à savoir les lectures et écritures
 * */
public class JsonMessageManagerService {


    private ObjectMapper mapper = new ObjectMapper();
    private DataWrapper dw ;
    private File file = new File("employee.json");



    /**
     * Méthode pour sauvegarder les Messages en base de donnée
     * */
    public    void  saveMessage(Message message){

        try{
            if ( file.exists()){
                dw = mapper.readValue(file , DataWrapper.class);
            } else dw = DataWrapper.getInstance();

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
    /**
     * Méthode pour Récupérer les Messages en base de donnée
     * */
    public List<Message> getAllMassage() throws  Exception{

        return  null;
    };
}
