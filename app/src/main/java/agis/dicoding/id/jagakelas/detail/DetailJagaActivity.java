package agis.dicoding.id.jagakelas.detail;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import agis.dicoding.id.jagakelas.R;
import agis.dicoding.id.jagakelas.model.Jaga;
import agis.dicoding.id.jagakelas.model.JagaResponse;
import agis.dicoding.id.jagakelas.rest.ApiClient;
import agis.dicoding.id.jagakelas.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailJagaActivity extends AppCompatActivity {
    TextView name, nis;
    String strName, strNis, formattedDate;
    Boolean statusJaga;
    List<Jaga> jaga;
    Menu menu;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_siswa);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Jaga");
        progressBar = findViewById(R.id.progressBar);
        showLoading();

        name = findViewById(R.id.nama);
        nis = findViewById(R.id.nisn);

        strName = getIntent().getStringExtra("name");
        strNis = getIntent().getStringExtra("nisn");

        name.setText(strName);
        nis.setText(strNis);
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
        formattedDate = sdf.format(date);

        getStatusJaga(strNis, formattedDate);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.status_jaga, menu);
        this.menu = menu;
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if  (item.getItemId() == R.id.statusJaga){
            if (!statusJaga){
                statusJaga = true;
                showLoading();
                insertJaga(strNis, formattedDate);
                item.setIcon(R.drawable.ic_square_check);
            }
            else {
                statusJaga = false;
                showLoading();
                deleteJaga(strNis, formattedDate);
                item.setIcon(R.drawable.ic_square_uncheck);
            }
            return true;
        }
        return false;
    }

    public void getStatusJaga(String nis, String date){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<JagaResponse> call = apiInterface.getStatusJaga(nis, date);

        call.enqueue(new Callback<JagaResponse>() {
            @Override
            public void onResponse(@NonNull Call<JagaResponse> call, @NonNull Response<JagaResponse> response) {
                jaga = new ArrayList<>();
                if  (response.body() != null) {
                    jaga = response.body().getJaga();
                    if (jaga.isEmpty()){
                        statusJaga = false;
                        menu.getItem(0).setIcon(R.drawable.ic_square_uncheck);
                        System.out.println("status jaga" + false);
                    }
                    else{
                        statusJaga = true;
                        System.out.println("status jaga " + true);
                        menu.getItem(0).setIcon(R.drawable.ic_square_check);
                    }
                    hideLoading();
                }else {
                    hideLoading();
                    Toast.makeText(getApplicationContext(), "Data gagal dimuat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<JagaResponse> call, @NonNull Throwable t) {

            }
        });

    }

    public void insertJaga(String nis, String date) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<JagaResponse> call = apiInterface.insertJaga(nis, date);

        call.enqueue(new Callback<JagaResponse>() {
            @Override
            public void onResponse(@NonNull Call<JagaResponse> call, @NonNull Response<JagaResponse> response) {

            }

            @Override
            public void onFailure(@NonNull Call<JagaResponse> call, @NonNull Throwable t) {

            }
        });
        hideLoading();
    }

    public void deleteJaga(String nis, String date){
        System.out.println("------------------------------------deleted");
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<JagaResponse> call = apiInterface.deletejaga(nis, date);


        call.enqueue(new Callback<JagaResponse>() {
            @Override
            public void onResponse(@NonNull Call<JagaResponse> call, @NonNull Response<JagaResponse> response) {

            }

            @Override
            public void onFailure(@NonNull Call<JagaResponse> call, @NonNull Throwable t) {

            }
        });
        hideLoading();
    }

    private void showLoading(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading(){
        progressBar.setVisibility(View.INVISIBLE);
    }
}
