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


    /**
     * Méthode pour sauvegarder les Chaines  en base de donnée
     * */
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

/**

 * Cette permet de mettre a jour la chaine si employé ajoute a InfoHub
 * juste en ajoutant son id a la liste des  employés de la chaine
  */
    public    void updateChannel(String  channelId, Channel newChannel) throws Exception {
        List<Channel> channels =  new JsonChannelManagerService().getAllChannel();

        if(channels != null){
            for (int i = 0; i < channels.size(); i++) {
                if (channels.get(i).getChannelId().equals(channelId)) {
                    channels.set(i, newChannel);
                    break;
                }
            }
            try{
                if(file.exists()){
                    dw =  DataWrapper.getInstance();
                    dw.setChannels(channels);
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

    /**
     * Cette methode permet d'obtenir la liste des chaines de notre Service d'information
     */
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
