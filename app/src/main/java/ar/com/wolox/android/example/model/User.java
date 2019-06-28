package ar.com.wolox.android.example.model;

import com.google.gson.annotations.SerializedName;

/**
 * User Class
 */
public class User {

    @SerializedName("id")
    private int id;
    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    private UserComplements mUserComplements;

    // Constructor vacio para instanciar
    public User() {
    }

    // Constructor para @GET de login
    public User(String email) {
        this.email = email;
    }

    // Constructor sobrecargado
    public User(int id, String username, String email, String password, UserComplements userComplements) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        mUserComplements = userComplements;
    }

    // Getter and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserComplements getUserComplements() {
        return mUserComplements;
    }

    public void setUserComplements(UserComplements userComplements) {
        mUserComplements = userComplements;
    }
}
