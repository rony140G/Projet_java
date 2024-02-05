package ui;
import entity.Account;
import usecase.LoginFailedException;
import usecase.LoginUseCase;

import java.util.Scanner;
public class LoginUI {
    private final Scanner scanner;
    private final LoginUseCase loginUseCase;

    public LoginUI() {
        this.scanner = new Scanner(System.in);
        this.loginUseCase = new LoginUseCase();
    }

    public void show() {
        System.out.println("***********LOGIN*********");
        System.out.print("Email: ");
        String mail = scanner.next();

        System.out.print("Password: ");
        String password = scanner.next();

        try {
            Account loggedInAccount = this.loginUseCase.login(mail, password);
            if(loggedInAccount.isAdmin()) new AdminMenuUI().show();
        } catch (LoginFailedException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
