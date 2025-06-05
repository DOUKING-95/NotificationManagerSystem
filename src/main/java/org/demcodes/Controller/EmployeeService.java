package org.demcodes.Controller;

import com.sun.security.jgss.GSSUtil;
import org.demcodes.Model.Employee;
import org.demcodes.Model.Message;
import org.demcodes.external_service.MailSender;

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

    public static  void setLoginMenu(String senderId) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println(" 1::: Pour se désabonné de Infohub");
        System.out.println(" 2::: Envoyer des messages ");
        System.out.println(" 3::: Envoyer un message Grouper(NB: Toutes les abonnés le receverons");
        int employeeCnxChoice = sc.nextInt();
        switch (employeeCnxChoice){
            case 1:
                // unsuscribe methode here
                break;
            case 2:
                List<Employee> employeeList =  new JsonEmployeeManagerService().getAllEmployee();
                if (employeeList != null){
                    for(int i = 0; i < employeeList.toArray().length ; i++){
                        System.out.println(i +":::" + employeeList.get(i).getFirstName() + " " + employeeList.get(i).getName());
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
                    for(int i = 0; i < employees.toArray().length ; i++){
                        Message message = ConsoleMessage.initMessage(senderId, employees.get(i).getEmployeeId());
                        new JsonMessageManagerService().saveMessage(message);
                        MailSender.sendEmail(employees.get(i).getEmail(), message.getMsgTitle(),message.getMessage());
                    }
                }
            break;

            default:
                System.out.println("Merci de choisir parmi les option ci dessus !");
        }
    }

}
