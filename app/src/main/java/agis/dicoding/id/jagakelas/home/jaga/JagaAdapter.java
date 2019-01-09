package agis.dicoding.id.jagakelas.home.jaga;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import agis.dicoding.id.jagakelas.R;
import agis.dicoding.id.jagakelas.detail.DetailJagaActivity;
import agis.dicoding.id.jagakelas.model.Student;

public class JagaAdapter extends RecyclerView.Adapter<JagaAdapter.JagaViewHolder> {
    private List<Student> listStudent;


    JagaAdapter(List listStudent) {
        this.listStudent = listStudent;
    }
    @NonNull
    @Override
    public JagaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_siswa, viewGroup, false);
        return new JagaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JagaViewHolder jagaViewHolder, final int position) {
        final Student s = listStudent.get(position);
        jagaViewHolder.absent.setText(String.valueOf(position+1));
        jagaViewHolder.name.setText(s.getNama());
        jagaViewHolder.nisn.setText(s.getNisn());
        jagaViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), DetailJagaActivity.class);
                i.putExtra("name", s.getNama());
                i.putExtra("nisn", s.getNisn());
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listStudent.size();
    }

    public Student getStudent(int position){
        return listStudent.get(position);
    }

    class JagaViewHolder extends RecyclerView.ViewHolder {
        TextView absent, name, nisn;
        CardView cv;

        JagaViewHolder(@NonNull View itemView) {
            super(itemView);
            absent = itemView.findViewById(R.id.absent);
            name = itemView.findViewById(R.id.nama);
            nisn = itemView.findViewById(R.id.nisn);
            cv = itemView.findViewById(R.id.card_view);
        }
    }
}
