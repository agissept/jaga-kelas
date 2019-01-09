package agis.dicoding.id.jagakelas.report.jaga;

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
import agis.dicoding.id.jagakelas.model.Data;
import agis.dicoding.id.jagakelas.model.Jaga;
import agis.dicoding.id.jagakelas.model.Student;

public class ReportJagaAdapter extends RecyclerView.Adapter<ReportJagaAdapter.JagaViewHolder> {
    private List<Jaga> listStudent;

    public ReportJagaAdapter(List listStudent) {
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
        final Jaga j = listStudent.get(position);
        final Student st = listStudent.get(position).getStudent();
        final Data dt = st.getData();
        jagaViewHolder.absent.setText(String.valueOf(position+1));
        jagaViewHolder.name.setText(dt.getNama());
        jagaViewHolder.nisn.setText(j.getNis());
        jagaViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), DetailJagaActivity.class);
                i.putExtra("name", dt.getNama());
                i.putExtra("nisn", j.getNis());
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listStudent.size();
    }

    public Jaga getStudent(int position){
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
