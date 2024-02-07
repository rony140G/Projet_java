package infrastructure;


import entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    private final Map<Integer, User> users = new HashMap<>();
    private int nextId = 1;

    public void createUser(User user) {
        user.setId(nextId++);
        users.put(user.getId(), user);
    }

    public User readUser(int id) {
        return users.get(id);
    }

    public List<User> readAllUsers() {
        return new ArrayList<>(users.values());
    }

    public void updateUser(User user) {
        users.put(user.getId(), user);
    }

    public void deleteUser(int id) {
        users.remove(id);
    }
}
