package agis.dicoding.id.jagakelas.model;

import java.util.List;

public class StudentResponse {
    List<Student> data;

    public List<Student> getStudents() {
        return data;
    }
    public void setStudents(List<Student> students) {
        this.data = students;
    }
}
