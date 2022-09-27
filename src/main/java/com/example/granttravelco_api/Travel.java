package com.example.granttravelco_api;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "travel")
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date entryDate;

    private String location;

    private String picture;

    private Double latitude;

    private Double longitude;

    private String address;

    private String diaryEntry;

    @ManyToOne
    @JoinColumn(name = "User_id")
    User user;

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public Date getEntryDate() {return entryDate;}

    public void setEntryDate(Date entryDate) {this.entryDate = entryDate;}

    public String getLocation() {return location;}

    public void setLocation(String location) {this.location = location;}

    public String getPicture() {return picture;}

    public void setPicture(String picture) {this.picture = picture;}

    public Double getLatitude() {return latitude;}

    public void setLatitude(Double latitude) {this.latitude = latitude;}

    public Double getLongitude() {return longitude;}

    public void setLongitude(Double longitude) {this.longitude = longitude;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public String getDiaryEntry() {return diaryEntry;}

    public void setDiaryEntry(String diaryEntry) {this.diaryEntry = diaryEntry;}
}
