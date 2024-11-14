package Models;

import java.io.Serializable;

public abstract class User implements Serializable {
    protected String userID;
    protected String password;
    protected String name;
    protected String role;
    protected boolean firstLogin;
    protected String gender;
    protected int age;

    @Override
    public String toString() {
        return userID + ','  + password + ',' + name + ',' + role + ','+ firstLogin + ',' + gender + ','+ age;
    }

    public User(){}

    public User(String userID, String name, String password, String role, String gender, int age) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.role = role;
        this.firstLogin = true;
        this.gender = gender;
        this.age = age;
    }

    public boolean login(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
        this.firstLogin = false;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}