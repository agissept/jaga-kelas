package agis.dicoding.id.jagakelas.model;

import java.util.List;

public class AngkatanResponse {
    List<Angkatan> data;

    public List<Angkatan> getAngkatan() {
        return data;
    }
    public void setAngkatan(List<Angkatan> angkatan) {
        this.data = angkatan;
    }
}
