package Models;

import java.io.Serializable;

public class Pharmacist extends User implements Serializable {
    public Pharmacist(String userID, String name, String password, String role, String gender, int age){
        super(userID, name, password,role, gender, age);
    }
}
