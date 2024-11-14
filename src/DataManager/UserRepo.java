package DataManager;

import Models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserRepo implements SerializableRepo<List<User>>{
    private static final String USERS_FILE = "C:\\Users\\Rabiuddin\\IdeaProjects\\untitled\\src\\DataManager\\users.txt";
    private List<User> users = new ArrayList<>();

    @Override
    public void saveData() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
            System.out.println("User data saved successfully.");
        }
    }

    @Override
    public void loadData() throws IOException, ClassNotFoundException {
        if (new File(USERS_FILE).exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
                users = (List<User>) ois.readObject();
                System.out.println("User data loaded successfully.");
            }
        }
    }

    @Override
    public List<User> getData() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
