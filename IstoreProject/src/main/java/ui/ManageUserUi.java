package ui;

import infrastructure.*;
import usecase.EmailValidator;

import java.util.Scanner;
public class ManageUserUi {
    private final Scanner scanner;
    private final infrastructure.ManageUserDao ManageUserDao;

    public ManageUserUi(){
        this.scanner = new Scanner(System.in);
        this.ManageUserDao = new ManageUserDao();
    }
    public void show() {
        boolean running = true;
        while(running) {
            System.out.println("*********************Manage user tools*******************");

            while (running) {
                System.out.println("Back press 0");
                System.out.println("To create user, press 1");
                System.out.println("To View all user, press 2");
                System.out.println("To delete user  press 3");
                System.out.println("To update 4");

                int choix = scanner.nextInt();

                switch (choix) {
                    case 0:
                        running = false;
                        break;
                    case 1:
                        new CreateAccount().show();
                        break;
                    case 2:
                        new ManageUserDao().ViewUser();
                        break;
                    case 3:
                        deleteUser();
                        break;
                    case 4:
                        updateUser();
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        }
    }
    private void deleteUser() {
        System.out.print("Enter the email to delete: ");
        String email = scanner.next();
        boolean isValidEmail = EmailValidator.isValidEmail(email);

        if (!isValidEmail) {
            System.out.println("Invalid email format. Please enter a valid email address.");
        } else {
            ManageUserDao.DeleteUser(email);
        }
    }
    private void updateUser(){
        System.out.println("Enter the old email: ");
        String oldEmail = scanner.next();
        boolean UserExiste = ManageUserDao.VerifyUserExiste(oldEmail);
        if (!UserExiste){
            System.out.println("Cet utilisateur ne fait pas parti de la base de donnée");
        }else{
            System.out.println("Enter New Mail: ");
            String newEmail = scanner.next();

            System.out.println("Enter New Role: ");
            String newRole = scanner.next();

            System.out.println("Enter New Pseudo: ");
            String newPseudo = scanner.next();

            ManageUserDao.UpdateUser(oldEmail,newEmail,newRole,newPseudo);
        }



    }
    public static void main(String[] args) {
        ManageUserUi manageUserUi = new ManageUserUi();
        manageUserUi.show();
    }
}
