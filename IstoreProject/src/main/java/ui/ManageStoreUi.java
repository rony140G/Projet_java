package ui;

import infrastructure.ManageStoreDao;
import infrastructure.ManageUserDao;

import java.util.*;

import static infrastructure.ManageStoreDao.insertStore;

public class ManageStoreUi {
    Scanner scanner = new Scanner(System.in);
    public void show() {
        boolean running = true;
        while (running) {
            System.out.println("********************* Manage store *******************");
            System.out.println("Back to admin menu, press 0");
            System.out.println("to Create new store, press 1 ");
            System.out.println("to VIEW store, press 2");
            System.out.println("to delete store , press 3");
            int choice = scanner.nextInt();

            scanner.nextLine();

            switch (choice) {
                case 0:
                    running = false;
                    break;
                case 1:
                    CreateStoreUi();
                    break;
                case 2:
                    new ManageStoreDao().ViewStore();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }


    public void CreateStoreUi(){
        System.out.println("Enter the name of the store ");
        String storeName = scanner.nextLine();

        if (insertStore(storeName)){
            System.out.println("Store created !!");
        }else {
            System.out.println("store don't created !");
        }
    }
    public static void main(String[] args) {
        ManageStoreUi manageStoreUi = new ManageStoreUi();
        manageStoreUi.show();
    }
}
