package org.demcodes.View;

public class MenuInfoHub {

    public static void  welcomeMsg(){
        System.out.println("Bienvenue sur InfoHub");
        System.out.println("InfoHub rendre l'information accessible a tous et n\'importe Òu");
    }

    public  static  void userMenu(){
        System.out.println("=============InfoHub==============");
        System.out.println();

        System.out.println(" 1::: Pour Créer un compte sur InfoHub");
        System.out.println(" 2::: Pour se connecter à  InfoHub");
        System.out.println(" 3::: Pour verifier que un Employé est abonné a InfoHub");
        System.out.println(" 4::: Pour voir la liste de employés sur InfoHub Appuyez ");
        System.out.println(" 5::: Pour  Quitter InfoHub");
    }

    public  static void subscribeChoiceMenu(){
        System.out.println("Merci de choisir :");
        System.out.println(" 1::: Pour être ajouter a InfoHub et recevoir des messages ");
        System.out.println(" 2::: Pour Ignore, Notez que avec cette option aucun  de vous données n'est sauvegarder ! Merci ");

    }
}


