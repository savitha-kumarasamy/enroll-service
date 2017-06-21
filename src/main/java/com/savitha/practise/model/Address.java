package com.savitha.practise.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

/**
 * Created by savithakumarasamy on 6/18/17.
 */
public class Address {

    @NotEmpty(message = "Address Line 1 cannot be empty")
    String addressLine1;
    String addressLine2;
    @NotEmpty(message = "City cannot be empty")
    String city;
    @Size(min = 2,max = 2, message = "Please enter 2 letter state code")
    @NotEmpty(message = "State cannot be empty")
    String state;
    @Size(min=5, max=5, message = "Not a valid ZIP code. Should be of length 5")
    int zipCode;

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public Address withAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public Address withAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Address withCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Address withState(String state) {
        this.state = state;
        return this;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public Address withZipCode(int zipCode) {
        this.zipCode = zipCode;
        return this;
    }

}
