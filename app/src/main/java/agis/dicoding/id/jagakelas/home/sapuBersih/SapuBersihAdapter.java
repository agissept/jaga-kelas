package agis.dicoding.id.jagakelas.home.sapuBersih;

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
import agis.dicoding.id.jagakelas.detail.DetailSapuBersihActivity;
import agis.dicoding.id.jagakelas.model.Student;

public class SapuBersihAdapter extends RecyclerView.Adapter<SapuBersihAdapter.SapuBersihViewHolder> {
    private List<Student> listStudent;

    SapuBersihAdapter(List listStudent) {
        this.listStudent = listStudent;
    }

    @NonNull
    @Override
    public SapuBersihViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_siswa, viewGroup, false);
        return new SapuBersihViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SapuBersihViewHolder sapuBersihViewHolder, final int position) {
        final Student s = listStudent.get(position);
        sapuBersihViewHolder.absent.setText(String.valueOf(position+1));
        sapuBersihViewHolder.name.setText(s.getNama());
        sapuBersihViewHolder.nisn.setText(s.getNisn());
        sapuBersihViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), DetailSapuBersihActivity.class);
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

    class SapuBersihViewHolder extends RecyclerView.ViewHolder {
        TextView absent, name, nisn;
        CardView cv;

        SapuBersihViewHolder(@NonNull View itemView) {
            super(itemView);
            absent = itemView.findViewById(R.id.absent);
            name = itemView.findViewById(R.id.nama);
            nisn = itemView.findViewById(R.id.nisn);
            cv = itemView.findViewById(R.id.card_view);
        }
    }
}
