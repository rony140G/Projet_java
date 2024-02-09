package usecase;
import java.sql.*;
import entity.Account;
import entity.AuthenticationDao;
import infrastructure.InMemoryAuthenticationDao;

public class LoginUseCase {
    private final AuthenticationDao authenticationDao = new InMemoryAuthenticationDao();

    public Account login(String mail, String password) throws LoginFailedException {
        return this.authenticationDao.login(mail, password);
    }
}
