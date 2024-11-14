package Models;

import java.io.Serializable;

public class Administrator extends User implements Serializable {
    public Administrator(String userID, String name,String password, String role, String gender, int age){
        super(userID, name,password, role, gender, age);
    }
}
