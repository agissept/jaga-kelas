package agis.dicoding.id.jagakelas.report.sapuBersih;

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
import agis.dicoding.id.jagakelas.model.Data;
import agis.dicoding.id.jagakelas.model.SapuBersih;
import agis.dicoding.id.jagakelas.model.Student;

public class ReportSapuBersihAdapter extends RecyclerView.Adapter<ReportSapuBersihAdapter.SapuBersihViewHolder> {
    private List<SapuBersih> listStudent;

    ReportSapuBersihAdapter(List listStudent) {
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
        final SapuBersih s = listStudent.get(position);
        final Student st = listStudent.get(position).getStudent();
        final Data dt = st.getData();
        sapuBersihViewHolder.absent.setText(String.valueOf(position+1));
        sapuBersihViewHolder.name.setText(dt.getNama());
        sapuBersihViewHolder.nisn.setText(s.getNis());
        sapuBersihViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), DetailSapuBersihActivity.class);
                i.putExtra("name", dt.getNama());
                i.putExtra("nisn", s.getNis());
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listStudent.size();
    }

    public SapuBersih getStudent(int position){
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
