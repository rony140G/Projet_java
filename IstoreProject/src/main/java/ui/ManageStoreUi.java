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
            System.out.println("to manage inventory, press 4");
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
        if (DeleteStore(storeName)){
            System.out.println("Store deleted !!");
        }else {
            System.out.println("store don't deleted !");
        }
    }


    public void CreateStoreUi(){
        System.out.println("Enter the name of the store to create ");
        String storeName = scanner.nextLine();

        if (CreateStore(storeName)){
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
            System.out.println("to manage employe inventory , press 5");
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
                case 5:
                    EmployeStoreUi();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void EmployeStoreUi() {
        boolean choix = true ;
        while(choix){
            System.out.println("To back press 0");
            System.out.println("To add employe to store, press 1 ");
            System.out.println("To delete employe to store, press 2");
            System.out.println("To view employe to store , press 3");
            int cas = scanner.nextInt();
            scanner.nextLine();
            switch (cas){
                case 0:
                    choix = false;
                    break;
                case 1:
                    addEmployeToStore();
                    break;
                case 2:
                    delEmployeToStore();
                    break;
                case 3:
                    viewEmployeStore();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }


    private void viewEmployeStore() {
        System.out.println("De quelle boutique souhaitez vous voire les employées ?");
        String Boutique = scanner.nextLine();
        int ID_store = new ManageStoreDao().getInventoryId(Boutique);
        ManageStoreDao.viewEmployeeStore(ID_store);
    }
    private void delEmployeToStore() {
        System.out.println("Dans quelle boutique voulez vous supprimer un employé");
        String Boutique = scanner.nextLine();
        int ID_Store = new ManageStoreDao().getInventoryId(Boutique);

        System.out.println("Email de l'employe");
        String Employee = scanner.nextLine();
        int ID_employee = getUserID(Employee);

        if(ManageStoreDao.deleteEmployeToStore(ID_employee,ID_Store)){
            System.out.println("Employé supprimé du store avec succes");
        }else  {
            System.out.println("echec lors de la suppression");
        }
    }

    private void addEmployeToStore() {
        System.out.println("Dans quelle boutique voulez vous ajouter votre employe");
        String Boutique = scanner.nextLine();
        int ID_Store = new ManageStoreDao().getInventoryId(Boutique);

        System.out.println("Email de l'employe");
        String Employee = scanner.nextLine();
        int ID_employee = getUserID(Employee);


        if (ManageStoreDao.addEmployeeToStore(ID_employee,ID_Store)){
            System.out.println("Employé ajouté a la boutique avec succés");
        }else {
            System.out.println("Employée non ajouté");
        }
    }

    private void DeletteToStock() {
        System.out.println("Dans quelle boutique voulez vous supprimer votre article");
        String Boutique = scanner.nextLine();
        int ID_s = new ManageStoreDao().getInventoryId(Boutique);

        System.out.println("Nom de l' article");
        String NomArticle = scanner.nextLine();

        System.out.println("Nombre à supprimeé");
        int NombreArticle = scanner.nextInt();

        ManageStoreDao.DeleteArticlesFromStore(NomArticle, NombreArticle,ID_s);

    }
    private void ViewArticle() {
        System.out.println("Entrez le nom de la boutique dont vous voulez voire l'inventaire :");
        String Boutique = scanner.nextLine();

        ManageStoreDao.ViewArticleInInventory(Boutique);
    }

    private void CreateArticle() {
        System.out.println("Dans quelle boutique souhaitez-vous ajouter un article ?");
        String store = scanner.nextLine();
        int ID_Store = new ManageStoreDao().getInventoryId(store);

        // Vérification si l'ID de la boutique est valide (supérieur à 0)
        if (ID_Store <= 0) {
            System.out.println("La boutique spécifiée n'existe pas. Veuillez réessayer.");
            return;
        }

        System.out.println("Entrez le nom de l'article :");
        String Article = scanner.nextLine();

        System.out.println("Entrez le prix de l'article :");
        int price = scanner.nextInt();

        System.out.println("Stock ?");
        int stock = scanner.nextInt();

        if (ManageStoreDao.InsertArticle(Article, price, stock, ID_Store)) {
            System.out.println("Article ajouté avec succès");
        } else {
            System.out.println("Erreur lors de l'ajout de l'article. Veuillez réessayer.");
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
        if (NombreArticle <= 0){
            System.out.println("La quantité à ajouter doit être supérieur à zéro");
            InventoryMenu();
        } else {
            ManageStoreDao.addArticle(NomArticle, NombreArticle,ID_s);
        }
    }
    public static void main(String[] args){
        ManageStoreUi manageStoreUi = new ManageStoreUi();
        manageStoreUi.show();
    }
}