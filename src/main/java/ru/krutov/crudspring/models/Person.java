package ru.krutov.crudspring.models;

import jakarta.validation.constraints.*;

public class Person {
    private int id;

    @NotEmpty (message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be btw 2 and 30 characters")
    private String name;
    @Min(value = 0, message = "Age must be greater than zero")
    private int age;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email must be Valid!")
    private String email;
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}",
            message = "Your Address should be in this format: Country, City, PostalCode(6 digits))")
    private String address;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person(){

    }

    public Person(int id, String name, int age, String email,String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }
    //Страна, Город, индекс (6цифр)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
