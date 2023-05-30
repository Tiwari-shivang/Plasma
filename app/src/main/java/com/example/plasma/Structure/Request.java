package com.example.plasma.Structure;

public class Request {
    public String Age, Gender, Weight, BMI, BMR, BloodGroup, Address, Mobile, hospitalName;
    public Request (String Age, String Gender, String Weight, String BMI, String BMR, String BloodGroup, String Address, String Mobile, String hospitalName){
        this.Age = Age;
        this.Gender = Gender;
        this.Weight = Weight;
        this.BMI = BMI;
        this.BMR = BMR;
        this.hospitalName = hospitalName;
        this.BloodGroup = BloodGroup;
        this.Address = Address;
        this.Mobile = Mobile;
    }
}
