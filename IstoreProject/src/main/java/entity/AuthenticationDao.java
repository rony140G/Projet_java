package entity;

import usecase.LoginFailedException;

public abstract class AuthenticationDao {
    public abstract Account login(String mail, String password) throws LoginFailedException;

}
