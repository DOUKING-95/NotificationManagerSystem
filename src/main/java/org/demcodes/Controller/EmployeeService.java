package org.demcodes.Controller;

import com.sun.security.jgss.GSSUtil;
import org.demcodes.Model.Employee;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.DoubleToIntFunction;

public class EmployeeService {

    private  JsonEmployeeManagerService jsonEmployeeManagerService;

    public  EmployeeService(JsonEmployeeManagerService jsonEmployeeManagerService){
        this.jsonEmployeeManagerService = jsonEmployeeManagerService;
    }

    public  static Employee initEmployee(){
        Scanner sc = new Scanner(System.in);
        String employeeId = UUID.randomUUID().toString();
        System.out.println("Enregistrement d'employé sur le Service de notification");

        System.out.println("Donner votre Prenom :");
        String prenom = sc.nextLine();
        System.out.println("Donner votre Nom :");
        String nom = sc.nextLine();
        System.out.println("Donner votre Email :");
        String email = sc.nextLine();
        System.out.println("Donner votre Mot de passe :");
        String motDePasse = sc.nextLine();
        return  new Employee( employeeId,nom, prenom, motDePasse, email);
    }

    public  static  void suscribeEmployee(Employee employee){};
    public  static  void unsuscribeEmployee(Employee employee){};

    public   Employee login(String email, String password) throws Exception {
        List<Employee> employeeList = jsonEmployeeManagerService.getAllEmployee();

        if(employeeList != null){
            for (Employee employee : employeeList){
                if(employee.getEmail().equals(email) && employee.getPassword().equals(password)){
                    System.out.println("Bienvenue Mr/ Mme" +employee.getFirstName() + " " + employee.getName());
                }
                else System.out.println("Vous n\'êtes  pas  inscrit sur InfoHub ! Merci de vous inscrire avant de vous connecter");
                return  employee;
            }
        } else {
            System.out.println("Aucun employé touver lors de la connection d'un employé");

        }
        return  null;
    }

}
