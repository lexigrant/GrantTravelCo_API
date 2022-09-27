package com.example.granttravelco_api;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_tbl")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String name;

    private String picture;

    private String googleSub;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Travel> travels;

    public Integer getId() {return id;}

    public Set<Travel> getTravels() {return travels;}
    public void setTravels(Set<Travel> travels) {this.travels = travels;}
    public void setId(Integer id) {this.id = id;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getPicture() {return picture;}

    public void setPicture(String picture) {this.picture = picture;}

    public String getGoogleSub() {return googleSub;}

    public void setGoogleSub(String googleSub) {this.googleSub = googleSub;}
}
