package com.example.application.views.helloworld;

import java.util.Date;

import javax.annotation.Nonnull;

public class Person {
    
    @Nonnull
    private String firstName;
    
    @Nonnull
    private String lastName;
    
    @Nonnull
    private String email;
    
    @Nonnull
    private Date birthday;
    
    @Nonnull
    private Integer id;
    
    @Nonnull
    private boolean subscriber;
    
    @Nonnull
    private String membership;
    
    @Nonnull
    private String pictureUrl;
    
    @Nonnull
    private String profession;
    
    private Address address;
    
    private Integer managerId;
    
    @Nonnull
    private boolean manager;
    
    @Nonnull
    private String status;
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }
    
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(final Date birthday) {
        this.birthday = birthday;
    }
    
    public boolean isSubscriber() {
        return this.subscriber;
    }
    
    public void setSubscriber(final boolean subscriber) {
        this.subscriber = subscriber;
    }
    
    public String getMembership() {
        return this.membership;
    }
    
    public void setMembership(final String membership) {
        this.membership = membership;
    }
    
    public String getPictureUrl() {
        return this.pictureUrl;
    }
    
    public void setPictureUrl(final String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
    
    public String getProfession() {
        return this.profession;
    }
    
    public void setProfession(final String profession) {
        this.profession = profession;
    }
    
    public Address getAddress() {
        return this.address;
    }
    
    public void setAddress(final Address address) {
        this.address = address;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(final Integer id) {
        this.id = id;
    }
    
    @Override
    public int hashCode() {
        return this.id;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof final Person other)) {
            return false;
        }
        return this.id == other.id;
    }
    
    public Integer getManagerId() {
        return this.managerId;
    }
    
    public void setManagerId(final Integer managerId) {
        this.managerId = managerId;
    }
    
    public boolean isManager() {
        return this.manager;
    }
    
    public void setManager(final boolean manager) {
        this.manager = manager;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(final String status) {
        this.status = status;
    }
}
// end::snippet[]
