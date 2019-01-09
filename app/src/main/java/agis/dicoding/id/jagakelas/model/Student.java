package agis.dicoding.id.jagakelas.model;


import java.io.Serializable;

public class Student implements Serializable {
    String nama, nis;
    Data data;
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNisn() {
        return nis;
    }

    public void setNisn(String nis) {
        this.nis = nis;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }
}
