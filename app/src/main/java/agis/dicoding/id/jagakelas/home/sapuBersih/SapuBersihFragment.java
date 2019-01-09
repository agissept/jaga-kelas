package agis.dicoding.id.jagakelas.home.sapuBersih;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import agis.dicoding.id.jagakelas.R;
import agis.dicoding.id.jagakelas.model.Angkatan;
import agis.dicoding.id.jagakelas.model.AngkatanResponse;
import agis.dicoding.id.jagakelas.model.Jurusan;
import agis.dicoding.id.jagakelas.model.JurusanResponse;
import agis.dicoding.id.jagakelas.model.Kelas;
import agis.dicoding.id.jagakelas.model.KelasResponse;
import agis.dicoding.id.jagakelas.model.StudentResponse;
import agis.dicoding.id.jagakelas.model.Student;
import agis.dicoding.id.jagakelas.rest.ApiClient;
import agis.dicoding.id.jagakelas.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SapuBersihFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Student> studentList;
    private List<Angkatan> angkatanList;
    private List<Jurusan> jurusanList;
    private List<Kelas> kelasList;
    private SapuBersihAdapter adapter;
    private Spinner spinnerAngkatan, spinnerJurusan, spinnerKelas;
    private ProgressBar progressBar;
    SwipeRefreshLayout swipe;
    View view;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rv_spinner, container, false);
        recyclerView = view.findViewById(R.id.rv_student);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        spinnerAngkatan = view.findViewById(R.id.spinner1);
        spinnerJurusan = view.findViewById(R.id.spinner2);
        spinnerKelas = view.findViewById(R.id.spinner3);
        progressBar = view.findViewById(R.id.progressBar);
        swipe = view.findViewById(R.id.sw_layout);

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadSpinnerAngkatan();
            }
        });
        showLoading();
        loadSpinnerAngkatan();

        spinnerAngkatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loadSpinnerJurusan(spinnerAngkatan.getSelectedItem().toString());
                showLoading();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerJurusan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loadSpinnerKelas(spinnerAngkatan.getSelectedItem().toString(), spinnerJurusan.getSelectedItem().toString());
                showLoading();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerKelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loadStudent(spinnerAngkatan.getSelectedItem().toString(), spinnerJurusan.getSelectedItem().toString(), spinnerKelas.getSelectedItem().toString());
                showLoading();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    private void loadSpinnerAngkatan() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<AngkatanResponse> getAngkatan = apiService.getAngkatan();

        getAngkatan.enqueue(new Callback<AngkatanResponse>() {
            @Override
            public void onResponse(@NonNull Call<AngkatanResponse> call, @NonNull Response<AngkatanResponse> response) {
                angkatanList = new ArrayList<>();
                if  (response.body() != null) {
                    angkatanList = response.body().getAngkatan();
                    List<String> listSpinner = new ArrayList<>();
                    for (int i = 0; i < angkatanList.size(); i++) {
                        listSpinner.add(angkatanList.get(i).getAngkatan());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(),
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerAngkatan.setAdapter(adapter);
                    loadSpinnerJurusan(spinnerAngkatan.getSelectedItem().toString());
                }else {
                    hideLoading();
                    Toast.makeText(view.getContext(), "Data gagal dimuat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AngkatanResponse> call, @NonNull Throwable t) {

            }
        });
    }

    private void loadSpinnerJurusan(final String angkatan) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<JurusanResponse> getJurusan = apiService.getJurusan(angkatan);

        getJurusan.enqueue(new Callback<JurusanResponse>() {
            @Override
            public void onResponse(@NonNull Call<JurusanResponse> call, @NonNull Response<JurusanResponse> response) {
                jurusanList = new ArrayList<>();
                if  (response.body() != null) {
                    jurusanList = response.body().getJurusan();
                    List<String> listSpinner = new ArrayList<>();
                    for (int i = 0; i < jurusanList.size(); i++){
                        listSpinner.add(jurusanList.get(i).getJurusan());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(),
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerJurusan.setAdapter(adapter);
                    loadSpinnerKelas(angkatan, spinnerJurusan.getSelectedItem().toString());
                }else {
                    hideLoading();
                    Toast.makeText(view.getContext(), "Data gagal dimuat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<JurusanResponse> call, @NonNull Throwable t) {

            }
        });
    }

    private void loadSpinnerKelas(final String angkatan, final String jurusan) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<KelasResponse> getKelas = apiService.getKelas(angkatan, jurusan);

        getKelas.enqueue(new Callback<KelasResponse>() {
            @Override
            public void onResponse(@NonNull Call<KelasResponse> call, @NonNull Response<KelasResponse> response) {
                kelasList = new ArrayList<>();
                if  (response.body() != null) {
                    kelasList = response.body().getKelas();
                    List<String> listSpinner = new ArrayList<>();
                    for (int i = 0; i < kelasList.size(); i++){
                        listSpinner.add(kelasList.get(i).getKelas());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(),
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerKelas.setAdapter(adapter);
                    loadStudent(angkatan, jurusan, spinnerKelas.getSelectedItem().toString());
                }else {
                    hideLoading();
                    Toast.makeText(view.getContext(), "Data gagal dimuat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<KelasResponse> call, @NonNull Throwable t) {

            }
        });
    }

    private void loadStudent(final String angkatan, final String jurusan, final String kelas) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<StudentResponse> getStudent = apiService.getStudent(angkatan, jurusan, kelas);

        getStudent.enqueue(new Callback<StudentResponse>() {
            @Override
            public void onResponse(@NonNull Call<StudentResponse> call, @NonNull Response<StudentResponse> response) {
                studentList = new ArrayList<>();
                if (response.body() != null) {
                    studentList = response.body().getStudents();
                    adapter = new SapuBersihAdapter(studentList);
                    recyclerView.setAdapter(adapter);
                    hideLoading();
                } else {
                    hideLoading();
                    Toast.makeText(view.getContext(), "Data gagal dimuat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<StudentResponse> call, @NonNull Throwable t) {

            }
        });
    }

    private void showLoading(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading(){
        progressBar.setVisibility(View.INVISIBLE);
        swipe.setRefreshing(false);
    }
}
