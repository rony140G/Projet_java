package ui;

import infrastructure.ManageStoreDao;



import java.util.*;

import static infrastructure.ManageStoreDao.*;

public class ManageStoreUi {
    Scanner scanner = new Scanner(System.in);
    public void show() {
        boolean running = true;
        while (running) {
            System.out.println("********************* Manage store *******************");
            System.out.println("Back to admin menu, press 0");
            System.out.println("to Create new store, press 1 ");
            System.out.println("to view store, press 2");
            System.out.println("to delete store , press 3");
            System.out.println("to manage store inventory store , press 4");
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
                case 3:
                    DeleteStoreUi();
                    break;
                case 4:
                    InventoryMenu();
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;

            }
        }
    }

    private void DeleteStoreUi() {
        System.out.println("Enter the name of the store ");
        String storeName = scanner.nextLine();
        if (deleteStore(storeName)){
            System.out.println("Store deleted !!");
        }else {
            System.out.println("store don't deleted !");
        }
    }


    public void CreateStoreUi(){
        System.out.println("Enter the name of the store to delete ");
        String storeName = scanner.nextLine();

        if (insertStore(storeName)){
            System.out.println("Store created !!");
        }else {
            System.out.println("store don't created !");
        }
    }

    public void InventoryMenu (){
        boolean bunning = true;
        while (bunning) {
            System.out.println("********************* Menu Inventory *******************");
            System.out.println("Back to manage store, press 0");
            System.out.println("to Create article, press 1 ");
            System.out.println("to view  article, press 2");
            System.out.println("to add article in inventory , press 3");
            System.out.println("to withdrawn article in inventory , press 4");
            int choice = scanner.nextInt();

            scanner.nextLine();

            switch (choice) {
                case 0:
                    bunning = false;
                    break;
                case 1:
                    CreateArticle();
                    break;
                case 2:
                    ViewArticle();
                    break;
                case 3:
                    AddToStock();
                    break;
                case 4:
                    DeletteToStock();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;

            }
        }
        new ManageStoreDao().manageinventory();
    }

    private void DeletteToStock() {
        System.out.println("Dans quelle boutique voulez vous supprimer votre article");
        String Boutique = scanner.nextLine();
        int ID_s = new ManageStoreDao().getInventoryId(Boutique);

        System.out.println("Nom de l' article");
        String NomArticle = scanner.nextLine();

        System.out.println("Nombre à supprimeé");
        int NombreArticle = scanner.nextInt();

        ManageStoreDao.DelArticle(NomArticle, NombreArticle,ID_s);
    }

    private void ViewArticle() {
        System.out.println("Entrez le nom de la boutique dont vous voulez voire l'inventaire :");
        String Boutique = scanner.nextLine();

        ManageStoreDao.ViewArticleInInventory(Boutique);
    }

    private void CreateArticle() {
        System.out.println("Dans quelle boutique souhaiter vous ajouter un article ?");
        String store = scanner.nextLine();
        int ID_Store = new ManageStoreDao().getInventoryId(store);

        System.out.println("Entrez le nom de l'article ");
        String Article = scanner.nextLine();

        System.out.println("Entrez le prix de l'article");
        int price =scanner.nextInt();

        System.out.println("Stock ?");
        int stock = scanner.nextInt();

        if(ManageStoreDao.InsertArticle(Article, price, stock, ID_Store)){
            System.out.println("Article ajouté avec succés");
        }
    }


    public void AddToStock(){
        System.out.println("Dans quelle boutique voulez vous ajouter votre article");
        String Boutique = scanner.nextLine();
        int ID_s = new ManageStoreDao().getInventoryId(Boutique);

        System.out.println("Nom de l' article");
        String NomArticle = scanner.nextLine();

        System.out.println("Nombre à ajouter");
        int NombreArticle = scanner.nextInt();

        ManageStoreDao.addArticle(NomArticle, NombreArticle,ID_s);
    }
    public static void main(String[] args) {
        ManageStoreUi manageStoreUi = new ManageStoreUi();
        manageStoreUi.show();
    }
}
