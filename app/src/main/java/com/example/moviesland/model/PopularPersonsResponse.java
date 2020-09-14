package com.example.moviesland.model;

import java.io.Serializable;
import java.util.List;

public class PopularPersonsResponse implements Serializable {

    private int code;
    private List<Person> personList;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
