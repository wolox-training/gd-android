package ar.com.wolox.android.example.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * UserComplements
 */
public class UserComplements implements Serializable {

    @SerializedName("picture")
    private String picture;
    @SerializedName("cover")
    private String cover;
    @SerializedName("description")
    private String description;
    @SerializedName("location")
    private String location;
    @SerializedName("name")
    private String name;
    @SerializedName("phone")
    private String phone;

    // Empty constructor for instance
    public UserComplements() {
    }

    // Overloaded constructor
    public UserComplements(String picture, String cover, String description, String location, String name, String phone) {
        this.picture = picture;
        this.cover = cover;
        this.description = description;
        this.location = location;
        this.name = name;
        this.phone = phone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
