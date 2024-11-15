package com.bank.offbank.model;

public class ClienteModel {

    private int id;
    private String name;
    private String cpf;
    private int age;
    private String email;
    private String telephone;
    private String cep;
    private int houseNumber;
    private char houseLetter;
    private String sex;
    private String photo;
    private LoginModel login;

    public ClienteModel() {
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
                ", sex='" + sex + '\'' +
                ", photo='" + photo + '\'' +
                ", login=" + login +
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
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
}
