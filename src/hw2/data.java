package hw2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class data {

    private SimpleStringProperty Name;
    private SimpleStringProperty email;
    private SimpleIntegerProperty mobileNumber;
    private SimpleStringProperty jobTitle;
    private SimpleStringProperty gender;
    private SimpleStringProperty number;
    public data() {
    }

    public data(String Name, String email, int mobileNumber, String jobTitle, String gender, String number) {
        this.Name = new SimpleStringProperty(Name);
        this.email = new SimpleStringProperty(email);
        this.mobileNumber = new SimpleIntegerProperty(mobileNumber);
        this.jobTitle = new SimpleStringProperty(jobTitle);
        this.gender = new SimpleStringProperty(gender);
        this.number = new SimpleStringProperty(number);
    }



    public String getName() {
        return Name.get();
    }

    public void setName(String Name) {
        this.Name = new SimpleStringProperty(Name);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email = new SimpleStringProperty(email);
    }

    public int getMobileNumber() {
        return mobileNumber.get();
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = new SimpleIntegerProperty(mobileNumber);
    }

    public String getJobTitle() {
        return jobTitle.get();
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = new SimpleStringProperty(jobTitle);
    }

    public String getGender() {
        return gender.get();
    }

    public void setGender(String gender) {
        this.gender = new SimpleStringProperty(gender);
    }

    public String getNumber() {
        return number.get();
    }

    public void setNumber(String number) {
        this.number = new SimpleStringProperty(number);
    }

}
