package org.demcodes;


import org.demcodes.Controller.*;
import org.demcodes.Model.Employee;
import org.demcodes.Model.Message;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        boolean quit = false;


        while (!quit){
            System.out.println("Bienvenue sur InfoHub");
            System.out.println("InfoHub rendre l'information accessible a tous et n\'importe Òu");

            System.out.println(" 1::: Pour Créer un compte sur InfoHub");
            System.out.println(" 2::: Pour se connecter à  InfoHub");
            System.out.println(" 3::: Pour verifier que un Employé est abonné a InfoHub");
            System.out.println(" 4::: Pour voir la liste de employés sur InfoHub Appuyez ");
            System.out.println(" 5::: Pour  rétirer un Employé");


            int choice =   sc.nextInt();

            switch (choice){
                case 1:
                    Employee employee =  EmployeeService.initEmployee();

                    System.out.println("Merci de choisir :");
                    System.out.println(" 1::: Pour être ajouter a InfoHub et recevoir des messages ");
                    System.out.println(" 2::: Pour Ignore, Notez que avec cette option aucun  de vous données n'est sauvegarder ! Merci ");
                    int channelChoice = sc.nextInt();
                    switch (channelChoice){
                        case 1:
                            new JsonEmployeeManagerService().saveEmployee(employee);
                            System.out.println(" ========== Bienvenue sur InfoHub ===========");
                            break;
                        case 2:
                            break;
                        default:
                            System.out.println("Merci de choisir parmi  l\'option 1 ou 2");
                    }


                    break;
                case 2:
                    //  connexion methode here

                    String email = sc.nextLine();
                    String password = sc.nextLine();
                    Employee currentEmployee =  new EmployeeService(null).login(email, password);
                   if(currentEmployee != null){
                       System.out.println(" 1::: Pour se désabonné de Infohub");
                       int employeeCnxChoice = sc.nextInt();
                       switch (employeeCnxChoice){
                           case 1:
                               // unsuscribe methode here
                               break;
                           default:
                               System.out.println("Merci de choisir parmi les option ci dessus !");
                       }
                   }

                    break;
                case 3:
                    // Verifier qu'un employe est bien Abonné
                    break;
                case  4:
                    //Voir la liste de Abonnés
                    new JsonEmployeeManagerService().getAllEmployee();
                    break;
                case 5:
                    // Retirer le employe de InfoHub
                    break;
                case 6:
                    quit = true;
                    break;

                default:
                    System.out.println("Merci de choice le parmi les numéro de spécifier ci dessus");

            }
        }




    }
}