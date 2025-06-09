package org.demcodes.Controller;

import com.sun.security.jgss.GSSUtil;
import org.demcodes.Model.Employee;
import org.demcodes.Model.Message;
import org.demcodes.external_service.MailSender;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.DoubleToIntFunction;


/**
 * La classe EmployeeService qui contient toute les méthodes pour le gestion des employés
 * */
public class EmployeeService {

    private  JsonEmployeeManagerService jsonEmployeeManagerService;

    // Constructeur de la classe employé
    public  EmployeeService(JsonEmployeeManagerService jsonEmployeeManagerService){
        this.jsonEmployeeManagerService = jsonEmployeeManagerService;
    }


    /**
     * Méthode pour initialiser un employé
     * juste ene spécifiant les attributs d'un agent
     * */
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


    /**
     * Méthode pour vérifier si un employé est bien abonnésur InfoHub
     * @Param : email pour eamil de l'employé en question
     * */
    public  boolean  isSuscribe(String email) throws Exception {
        List<Employee> employeeList = jsonEmployeeManagerService.getAllEmployee();
        if(employeeList != null){
            for (Employee employee : employeeList){
                if(employee.getEmail().equals(email) && employee.getSuscribeStatus()){
       return  true;
                }
                else return  false;
            }
        } else {
            System.out.println("Aucun employé touver lors de la connection d'un employé");

        }
        return  false;
    }


    /**
     * Méthode pour login qui permet à  un employe de ce connecter à Infohub
     * @Param : email
     * @Param : password
     * */
    public   Employee login(String email, String password) throws Exception {
        List<Employee> employeeList = new JsonEmployeeManagerService().getAllEmployee();

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


    /**
     * Cette méthode permtet afficher le menu de l'employé connecté
     */


    public static  void setLoginMenu(String senderId) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println(" 1::: Pour se désabonné de Infohub");
        System.out.println(" 2::: Envoyer un message à un employé particulier  ");
        System.out.println(" 3::: Envoyer un message Grouper(NB: Toutes les abonnés le receverons");
        int employeeCnxChoice = sc.nextInt();
        switch (employeeCnxChoice){
            case 1:
              new JsonEmployeeManagerService().removeEmployee(senderId);
                break;
            case 2:
                List<Employee> employeeList =  new JsonEmployeeManagerService().getAllEmployee();
                if (employeeList != null){
                    for(int i = 0; i < employeeList.toArray().length ; i++){
                        if (!Objects.equals(employeeList.get(i).getEmployeeId(), senderId)){
                            System.out.println(i +":::" + employeeList.get(i).getFirstName() + " " + employeeList.get(i).getName());
                        }

                    }
                    System.out.println("Donner l'indice de la personne a qui vous voulez enoyez une message ");
                    int indexDestinateurEmployee = sc.nextInt();

                    String receiverId = employeeList.get(indexDestinateurEmployee).getEmployeeId();

                   Message message = ConsoleMessage.initMessage(senderId, receiverId);

                   new JsonMessageManagerService().saveMessage(message);
                    MailSender.sendEmail(employeeList.get(indexDestinateurEmployee).getEmail(), message.getMsgTitle(),message.getMessage());


                }
            break;
            case 3:
                List<Employee> employees =  new JsonEmployeeManagerService().getAllEmployee();
                if (employees != null){
                    for(int i = 0; i < employees.toArray().length ; i++) {

                        if (!Objects.equals(employees.get(i).getEmployeeId(), senderId)) {
                            Message message = ConsoleMessage.initMessage(senderId, employees.get(i).getEmployeeId());
                            new JsonMessageManagerService().saveMessage(message);
                            MailSender.sendEmail(employees.get(i).getEmail(), message.getMsgTitle(), message.getMessage());
                        }

                    }}}}}