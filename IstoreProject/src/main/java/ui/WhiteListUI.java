package ui;

import infrastructure.*;
import usecase.EmailValidator;
import java.util.Scanner;
public class WhiteListUI {
    private final Scanner scanner;
    private final WhiteListDao whiteListDao;

    public WhiteListUI() {
        this.scanner = new Scanner(System.in);
        this.whiteListDao = new WhiteListDao();
    }

    public void show() {
        boolean running = true;

        while (running) {
            System.out.println("********************* WHITELIST E-MAILS *******************");
            System.out.println("Back to admin menu, press 0");
            System.out.println("Add e-mails to whitelist, press 1");
            System.out.println("Delete e-mails from whitelist, press 2");
            System.out.println("View whitelist, press 3");

            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    running = false;
                    break;
                case 1:
                    insertMailToWhiteList();
                    break;
                case 2:
                    deleteMailFromWhiteList();
                    break;
                case 3:
                    whiteListDao.viewWhiteList();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
    private void deleteMailFromWhiteList() {
        System.out.print("Enter the email to delete: ");
        String email = scanner.next();
        boolean isValidEmail = EmailValidator.isValidEmail(email);

        if (!isValidEmail) {
            System.out.println("Invalid email format. Please enter a valid email address.");
        } else {
            whiteListDao.deleteFromWhiteList(email);
        }
    }
    private void insertMailToWhiteList() {
        System.out.print("Enter the email to add: ");
        String email = scanner.next();
        boolean isValidEmail = EmailValidator.isValidEmail(email);

        if (!isValidEmail) {
            System.out.println("Invalid email format. Please enter a valid email address.");
        } else {
            whiteListDao.insertIntoWhiteList(email);
        }
    }
}
