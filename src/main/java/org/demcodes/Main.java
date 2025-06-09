package org.demcodes;


import org.demcodes.Controller.*;
import org.demcodes.Model.Channel;
import org.demcodes.Model.Employee;
import org.demcodes.Model.Message;
import org.demcodes.View.MenuInfoHub;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        boolean quit = false;

        List<Channel> channelList = new  JsonChannelManagerService().getAllChannel();
        if( channelList.isEmpty()){
           new ChannelService(null, null).defaultChannel();
        }
        MenuInfoHub.welcomeMsg();
        while (!quit){
            MenuInfoHub.userMenu();

            int choice =   sc.nextInt();

            switch (choice){
                case 1:
                    Employee employee =  EmployeeService.initEmployee();

                   MenuInfoHub.subscribeChoiceMenu();
                    int channelChoice = sc.nextInt();
                    switch (channelChoice){
                        case 1:
                            System.out.println(employee);
                            new JsonEmployeeManagerService().saveEmployee(employee);
                           // List<Channel> channels =  new JsonChannelManagerService().getAllChannel();
                          //  for(Channel channel : channels ){
                         //       new ChannelService(null, null).addToChannelMembers(channel.getChannelId(),employee.getEmployeeId());
                          //  }
                            System.out.println(" ========== Bienvenue sur InfoHub ===========");
                            System.out.println();
                           // System.out.println("Voici les chaines auquels ou abonnée sur InfoHub");
                          //  channels.forEach((channel -> System.out.println(channel.getChannelName())));

                            break;
                        case 2:
                            break;
                        default:
                            System.out.println("Merci de choisir parmi  l\'option 1 ou 2");
                    }

                    break;
                case 2:

                    System.out.println("Donner votre Email");
                    sc.nextLine();
                    String email = sc.nextLine();
                    System.out.println("Donner votre mot de passe ");
                    String password = sc.nextLine();
                    Employee currentEmployee =  new EmployeeService(null).login(email, password);
                   if(currentEmployee != null){
                      EmployeeService.setLoginMenu(currentEmployee.getEmployeeId());
                   }
                    System.out.println("Erreur de connecxion : Merci de verifier vos identifiants");
                    break;
                case 3:

                    String employeeEmail = sc.nextLine();
                   boolean suscribtionResult = new EmployeeService(null).isSuscribe(employeeEmail);
                   if(suscribtionResult) System.out.println("Sans surprise c\'est bien un membre de InfoHub, Merci pour la fidélité");
                   else {
                       System.out.println("Ooops vous n\'êtes pas abonné a InfoHub , Merci de vous abonné !");
                   }
                    break;
                case  4:

                   List<Employee> employees = new JsonEmployeeManagerService().getAllEmployee();
                    for (int i = 0; i < employees.size(); i++) {
                        System.out.println("Index: " + i + ", : " + employees.get(i).getFirstName() + " " + employees.get(i).getName());
                    }
                    break;
                case 5:
                    quit = true;
                    break;

                default:
                    System.out.println("Merci de choice le parmi les numéro de spécifier ci dessus");

            }
        }

    }
}