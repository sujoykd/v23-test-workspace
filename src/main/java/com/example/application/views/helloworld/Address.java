package com.example.application.views.helloworld;

public class Address {
    private String street;
    private String pincode;

    public String getStreet() {
        return this.street;
    }

    public String getPincode() {
        return this.pincode;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public void setPincode(final String pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return "Address [ pincode=" + this.pincode + "]";
    }

}
