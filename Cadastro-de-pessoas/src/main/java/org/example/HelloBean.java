package org.example;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Random;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Set<Integer> usedIds = ConcurrentHashMap.newKeySet();


    private int id;
    private String name;
    private int age;
    private String gender;
    private String cep;
    private int num;
    private String rua;
    private String estado;
    private String cidade;

    public int getId() {
        Random random = new Random();
        int id;
        do {
            id = random.nextInt(1000000); // Modify the range as needed
        } while (usedIds.contains(id));
        usedIds.add(id);
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}