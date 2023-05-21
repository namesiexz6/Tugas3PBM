package com.example.tugas3_2008107010089.model;

public class Data {
    private String number,npm,nama,jurusan;

    public Data(){

    }
    public Data(String number, String npm, String nama, String jurusan){
        this.number = number;
        this.npm = npm;
        this.nama = nama;
        this.jurusan = jurusan;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }
}
