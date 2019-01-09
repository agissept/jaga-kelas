package agis.dicoding.id.jagakelas.report.report;

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
import agis.dicoding.id.jagakelas.detail.DetailFinalReportActivity;
import agis.dicoding.id.jagakelas.model.Report;

public class FinalReportAdapter extends RecyclerView.Adapter<FinalReportAdapter.FinalReportViewHolder> {
    private List<Report> listStudent;

    public FinalReportAdapter(List listStudent) {
        this.listStudent = listStudent;
    }
    @NonNull
    @Override
    public FinalReportViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_siswa, viewGroup, false);
        return new FinalReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FinalReportViewHolder jagaViewHolder, final int position) {
        final Report r = listStudent.get(position);
        jagaViewHolder.absent.setText(String.valueOf(position+1));
        jagaViewHolder.name.setText(r.getName());
        jagaViewHolder.nisn.setText(r.getNisn());
        jagaViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), DetailFinalReportActivity.class);
                i.putExtra("name", r.getName());
                i.putExtra("nisn", r.getNisn());
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listStudent.size();
    }

    public Report getStudent(int position){
        return listStudent.get(position);
    }

    class FinalReportViewHolder extends RecyclerView.ViewHolder {
        TextView absent, name, nisn;
        CardView cv;

        FinalReportViewHolder(@NonNull View itemView) {
            super(itemView);
            absent = itemView.findViewById(R.id.absent);
            name = itemView.findViewById(R.id.nama);
            nisn = itemView.findViewById(R.id.nisn);
            cv = itemView.findViewById(R.id.card_view);
        }
    }
}
