package infrastructure;

import entity.Account;
import entity.AdminAccount;
import entity.AuthenticationDao;
import usecase.LoginFailedException;

public class InMemoryAuthenticationDao extends AuthenticationDao {

    private static final String ADMIN_MAIL = "admin@supinfo.com";
    private static final String ADMIN_PWD = "admin";
    @Override
    public Account login(String mail, String password) throws LoginFailedException {
        if(ADMIN_MAIL.equals(mail) && ADMIN_PWD.equals(password)) return new AdminAccount();
        throw new LoginFailedException("LOGIN FAILED");
    }
}
