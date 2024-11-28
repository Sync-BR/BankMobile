package com.bank.offbank.model;

import java.io.Serializable;

public class ClienteModel implements Serializable {

    private int id;
    private String name;
    private String cpf;
    private int age;
    private String email;
    private String telephone;
    private String cep;
    private int houseNumber;
    private char houseLetter;
    private int sex;
    private String photo;
    private LoginModel login;
    private CoinsModel coins;


    public ClienteModel(String name, String cpf, int age, String email, String telephone, int sex) {
        this.name = name;
        this.cpf = cpf;
        this.age = age;
        this.email = email;
        this.telephone = telephone;
        this.sex = sex;
    }

    public ClienteModel(String name, String cpf, int age, String email, String telephone, String cep, int houseNumber, char houseLetter, int sex) {
        this.name = name;
        this.cpf = cpf;
        this.age = age;
        this.email = email;
        this.telephone = telephone;
        this.cep = cep;
        this.houseNumber = houseNumber;
        this.houseLetter = houseLetter;
        this.sex = sex;
    }

    public ClienteModel(ClienteModel clienteModel, LoginModel login, CoinsModel coins) {
        this.name = clienteModel.getName();
        this.cpf = clienteModel.getCpf();
        this.age = clienteModel.getAge();
        this.email = clienteModel.getEmail();
        this.telephone = clienteModel.getTelephone();
        this.cep = clienteModel.getCep();
        this.houseNumber = clienteModel.getHouseNumber();
        this.houseLetter = clienteModel.getHouseLetter();
        this.sex = clienteModel.getSex();
        this.login = login;
        this.coins = coins;
    }

    public ClienteModel(ClienteModel client) {
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.age = client.getAge();
        this.email = client.getEmail();
        this.telephone = client.getTelephone();
        this.cep = client.getCep();
        this.houseNumber = client.getHouseNumber();
        this.houseLetter = client.getHouseLetter();
        this.sex = client.getSex();
        this.photo = client.getPhoto();
        this.login = client.getLogin();
        this.coins = client.getCoins();
    }


    @Override
    public String toString() {
        return "ClienteModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", cep='" + cep + '\'' +
                ", houseNumber=" + houseNumber +
                ", houseLetter=" + houseLetter +
                ", sex=" + sex +
                ", photo='" + photo + '\'' +
                ", login=" + login +
                ", coins=" + coins +
                '}';
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public char getHouseLetter() {
        return houseLetter;
    }

    public void setHouseLetter(char houseLetter) {
        this.houseLetter = houseLetter;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LoginModel getLogin() {
        return login;
    }

    public void setLogin(LoginModel login) {
        this.login = login;
    }

    public CoinsModel getCoins() {
        return coins;
    }

    public void setCoins(CoinsModel coins) {
        this.coins = coins;
    }
}
