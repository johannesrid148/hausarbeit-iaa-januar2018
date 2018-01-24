package de.nordakademie.iaa.user.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {

    private String userID;
    private String name;
    private String email;
    private String password;

    //Constructor
    public User(String userID,String name,String email,String password){
        this.email=email;
        this.name=name;
        this.password=password;
        this.userID=userID;
    }

    //Getter
    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    //Methoden

}
