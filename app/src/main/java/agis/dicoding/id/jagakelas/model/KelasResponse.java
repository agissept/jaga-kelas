package agis.dicoding.id.jagakelas.model;

import java.util.List;

public class KelasResponse {
    List<Kelas> data;

    public List<Kelas> getKelas() {
        return data;
    }
    public void setKelas(List<Kelas> kelas) {
        this.data = kelas;
    }
}
