package org.example;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import java.util.UUID;

@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {


    private String id;
    private String name;
    private int age;
    private String gender;
    private String cep;
    private float num;
    private String rua;
    private String estado;
    private String cidade;

    public HelloBean() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public float getNum() {
        return num;
    }

    public void setNum(float num) {
        this.num = num;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado != null && estado.length() == 2) {
            this.estado = estado.toUpperCase();
        } else {
            this.estado = "";
        }
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}