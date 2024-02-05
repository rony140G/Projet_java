package entity;

public class AdminAccount extends Account {
    @Override
    public boolean isAdmin() {
        return true;
    }

}
