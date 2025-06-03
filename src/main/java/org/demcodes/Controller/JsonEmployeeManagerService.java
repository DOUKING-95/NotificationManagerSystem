package org.demcodes.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.demcodes.Model.Employee;
import org.demcodes.wrapper.DataWrapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonEmployeeManagerService {



    private  ObjectMapper mapper = new ObjectMapper();
    private DataWrapper dw ;
    private File file = new File("employee.json");


    public    void  saveEmployee(Employee employee){

        try{
            if ( file.exists()){
              dw = mapper.readValue(file , DataWrapper.class);

            } else dw = DataWrapper.getDataWrapperInstance();

            dw.getEmployees().add(employee);
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, dw);
            System.out.println("L\'employe à été bien enregistrer avec Succes");



        }catch (IOException e){
            System.err.println("Erreur lors de \' enregistrement  de l'employe" + e.getMessage());
        }



    };


    public    void updateEmployee(String employeeId, Employee employee) throws Exception {

        List<Employee> employees = getAllEmployee();

        if(employees != null){

            for(Employee employeeData : employees){
                if(employeeData.getEmployeeId() == employee.getEmployeeId()){
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
                   dw =  DataWrapper.getDataWrapperInstance();
               }

            } catch (IOException e){
                System.out.println("Erreur lors de modification de l\'employee" + e.getMessage());
            }

        }else {
            System.out.println("La liste des Employee est vide lors de Modifications");
        }

    };

    public    void deleteEmployee(Long employeeId){};

    public    void getEmployeeById(Long employeeId){

    };

    public List<Employee> getAllEmployee() throws Exception {
        try {
            if (file.exists()) {
                dw = mapper.readValue(file, DataWrapper.class);
            } else {
                System.out.println("Le fichier n'existe pas lors de la lecture des employés.");
                dw =  DataWrapper.getDataWrapperInstance();
            }

            System.out.println("La liste des employés a été bien récupérée avec succès.");

        } catch (IOException e) {
            System.err.println("Erreur lors de la récupération des employés : " + e.getMessage());
            dw =  DataWrapper.getDataWrapperInstance();
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
