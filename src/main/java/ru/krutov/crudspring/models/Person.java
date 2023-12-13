package ru.krutov.crudspring.models;

import jakarta.validation.constraints.*;

public class Person {
    private int person_id;

    @NotEmpty (message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be btw 2 and 30 characters")
    private String name;
    @Min(value = 0, message = "Age must be greater than zero")
    private int birthYear;

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }


    public Person(){

    }

    public Person(int person_id, String name, int birthyear) {
        this.person_id = person_id;
        this.name = name;
        this.birthYear = birthyear;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
