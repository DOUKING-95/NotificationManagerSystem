package org.demcodes.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.demcodes.Model.Employee;
import org.demcodes.wrapper.DataWrapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JsonEmployeeManagerService {



    private  ObjectMapper mapper = new ObjectMapper();

    private DataWrapper dw ;
    private File file = new File("employee.json");

    /**
     * Méthode pour sauvegarder les Employé en base de donnée
     * */
    public void saveEmployee(Employee employee) {
        System.out.println("Chemin du fichier: " + file.getAbsolutePath());

        ObjectMapper mapper = new ObjectMapper();
        // Configuration importante pour éviter les boucles
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.WRITE_SELF_REFERENCES_AS_NULL, true);

        try {
            DataWrapper dw;

            if (file.exists() && file.length() > 0) {
                System.out.println("Lecture du fichier existant...");
                dw = mapper.readValue(file, DataWrapper.class);
                // Réassigner l'instance singleton
                DataWrapper.instance = dw;
            } else {
                System.out.println("Création nouvelle instance");
                dw = DataWrapper.getInstance();
            }

            System.out.println("Employés avant ajout: " + dw.getEmployees().size());

            // Vérifier si l'employé existe déjà
            boolean exists = dw.getEmployees().stream()
                    .anyMatch(e -> e.getEmployeeId().equals(employee.getEmployeeId()));

            if (!exists) {
                dw.getEmployees().add(employee);
                System.out.println("Employé ajouté: " + employee);

                // Sauvegarde
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, dw);

                // Vérification
                DataWrapper verification = mapper.readValue(file, DataWrapper.class);
                System.out.println("Employés après sauvegarde: " + verification.getEmployees().size());
                System.out.println("Employé enregistré avec succès");
            } else {
                System.out.println("L'employé existe déjà");
            }

        } catch (IOException e) {
            System.err.println("Erreur d'enregistrement: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Méthode pour modifier un Employé en base de donnée
     * */
    public    void updateEmployee(String employeeId, Employee employee) throws Exception {

        List<Employee> employees = getAllEmployee();

        if(employees != null){

            for(Employee employeeData : employees){
                if(Objects.equals(employeeData.getEmployeeId(), employee.getEmployeeId())){
                    employeeData = employee;

                }
            }

            try{
               if(file.exists()){
                   dw.getEmployees().addAll(employees);
                   mapper.writerWithDefaultPrettyPrinter().writeValue(file,dw);
                   System.out.println("l\'employe à été bien mise à jour");
               }
                    else{
                   System.out.println("Le fichier n'existe pas lors de la modification de l\'employe");
                   dw =  DataWrapper.getInstance();
               }

            } catch (IOException e){
                System.out.println("Erreur lors de modification de l\'employee" + e.getMessage());
            }

        }else {
            System.out.println("La liste des Employee est vide lors de Modifications");
        }

    };

    /**
     * Supprime un employé du fichier JSON basé sur son ID
     * @param employeeId L'ID de l'employé à supprimer
     */



    /**
     * Méthode pour Supprimer un Employé en base de donnée
     * */
    public void removeEmployee(String employeeId) {
        System.out.println("Chemin du fichier: " + file.getAbsolutePath());

        ObjectMapper mapper = new ObjectMapper();

        // Configuration identique à saveEmployee
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.WRITE_SELF_REFERENCES_AS_NULL, true);

        try {
            
            if (file.exists() && file.length() > 0) {
                System.out.println("Lecture du fichier existant...");
                dw = mapper.readValue(file, DataWrapper.class);
                DataWrapper.instance = dw;
            } else {
                System.out.println("Fichier non trouvé - aucune suppression possible");
                return;
            }

            System.out.println("Employés avant suppression: " + dw.getEmployees().size());


            boolean removed = dw.getEmployees().removeIf(
                    e -> e.getEmployeeId().equals(employeeId)
            );

            if (removed) {
                System.out.println("Employé avec ID " + employeeId + " supprimé");


                mapper.writerWithDefaultPrettyPrinter().writeValue(file, dw);

                // Vérification
                DataWrapper verification = mapper.readValue(file, DataWrapper.class);
                System.out.println("Employés après suppression: " + verification.getEmployees().size());
            } else {
                System.out.println("Aucun employé trouvé avec l'ID " + employeeId);
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de la suppression: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public    void getEmployeeById(Long employeeId){

    };

    /**
     * Méthode pour Récupérer les Employés en base de donnée
     * */
    public List<Employee> getAllEmployee() throws Exception {
        try {
            if (file.exists()) {
                dw =  DataWrapper.getInstance();
                dw = mapper.readValue(file, DataWrapper.class);
            } else {
                System.out.println("Le fichier n'existe pas lors de la lecture des employés.");
                dw =  DataWrapper.getInstance();
            }

            System.out.println("La liste des employés a été bien récupérée avec succès.");

        } catch (IOException e) {
            System.err.println("Erreur lors de la récupération des employés : " + e.getMessage());
            dw =  DataWrapper.getInstance();
        }


        if (dw.getEmployees() != null) {
            for (Employee employee : dw.getEmployees()) {
                System.out.println(employee);
            }
        } else {
            System.out.println("Aucun employé trouvé.");
            dw.setEmployees(new ArrayList<>());
        }

        return dw.getEmployees();
    }

}
