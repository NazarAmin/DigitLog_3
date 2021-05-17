package com.example.digitlog;

public class Users {
    String user;
    String password;
    String phone_number;
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public Users(String user, String password, String phone_number, String email){
        user = this.user;
        password = this.password;
        phone_number = this.phone_number;
        email = this.email;

    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Users (){

}
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
