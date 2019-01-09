package agis.dicoding.id.jagakelas.report.report;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import agis.dicoding.id.jagakelas.R;
import agis.dicoding.id.jagakelas.model.Report;
import agis.dicoding.id.jagakelas.model.ReportResponse;
import agis.dicoding.id.jagakelas.rest.ApiClient;
import agis.dicoding.id.jagakelas.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FinalReportFrament extends Fragment {
    private RecyclerView recyclerView;
    private List<Report> yearList, monthList, dayList, studentList;
    private FinalReportAdapter adapter;
    private Spinner spinnerYear, spinnerMonth, spinnerDay;
    private ProgressBar progressBar;
    SwipeRefreshLayout swipe;
    View view;
    String strDate;
    List<String> listSpinner;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rv_spinner, container, false);
        recyclerView = view.findViewById(R.id.rv_student);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        spinnerYear = view.findViewById(R.id.spinner1);
        spinnerMonth = view.findViewById(R.id.spinner2);
        spinnerDay = view.findViewById(R.id.spinner3);
        progressBar = view.findViewById(R.id.progressBar);
        swipe = view.findViewById(R.id.sw_layout);

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadSpinnerTahun();
            }
        });

        showLoading();
        loadSpinnerTahun();

        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loadSpinnerMonth(spinnerYear.getSelectedItem().toString());
                showLoading();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loadSpinnerDay(spinnerYear.getSelectedItem().toString(), spinnerMonth.getSelectedItem().toString());
                showLoading();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loadStudent(spinnerYear.getSelectedItem().toString(), spinnerMonth.getSelectedItem().toString(), spinnerDay.getSelectedItem().toString());
                showLoading();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    private void loadSpinnerTahun() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ReportResponse> getYear = apiService.getReport();

        getYear.enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(@NonNull Call<ReportResponse> call, @NonNull Response<ReportResponse> response) {
                yearList = new ArrayList<>();
                if  (response.body() != null) {
                    yearList = response.body().getData();
                    listSpinner = new ArrayList<>();
                    for (int i = 0; i < yearList.size(); i++){
                        strDate = yearList.get(i).getDate();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
                        try {
                            Date date = dateFormat.parse(strDate);
                            strDate = yearFormat.format(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if(!listSpinner.contains(strDate)){
                            listSpinner.add(strDate);
                        }
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(),
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerYear.setAdapter(adapter);
                    loadSpinnerMonth(spinnerYear.getSelectedItem().toString());
                }else {
                    hideLoading();
                    Toast.makeText(view.getContext(), "Data gagal dimuat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ReportResponse> call, @NonNull Throwable t) {

            }
        });
    }

    private void loadSpinnerMonth(final String year) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ReportResponse> getJurusan = apiService.getReportMonth(year);

        getJurusan.enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(@NonNull Call<ReportResponse> call, @NonNull Response<ReportResponse> response) {
                monthList = new ArrayList<>();
                if  (response.body() != null) {
                    monthList = response.body().getData();
                    listSpinner = new ArrayList<>();
                    for (int i = 0; i < monthList.size(); i++){
                        strDate = monthList.get(i).getDate();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
                        try {
                            Date date = dateFormat.parse(strDate);
                            strDate = monthFormat.format(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if(!listSpinner.contains(strDate)){
                            listSpinner.add(strDate);
                        }
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(),
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerMonth.setAdapter(adapter);
                    loadSpinnerDay(year, spinnerMonth.getSelectedItem().toString());
                }else {
                    hideLoading();
                    Toast.makeText(view.getContext(), "Data gagal dimuat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ReportResponse> call, @NonNull Throwable t) {

            }
        });
    }

    private void loadSpinnerDay(final String year, final String month) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ReportResponse> getDay = apiService.getReportDay(year, month);

        getDay.enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(@NonNull Call<ReportResponse> call, @NonNull Response<ReportResponse> response) {
                dayList = new ArrayList<>();
                if (response.body() != null) {
                    dayList = response.body().getData();
                    listSpinner = new ArrayList<>();
                    for (int i = 0; i < dayList.size(); i++) {
                        strDate = dayList.get(i).getDate();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
                        try {
                            Date date = dateFormat.parse(strDate);
                            strDate = dayFormat.format(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (!listSpinner.contains(strDate)) {
                            listSpinner.add(strDate);
                        }
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(),
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerDay.setAdapter(adapter);
                    loadStudent(year, month, spinnerDay.getSelectedItem().toString());
                } else {
                    hideLoading();
                    Toast.makeText(view.getContext(), "Data gagal dimuat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ReportResponse> call, @NonNull Throwable t) {

            }
        });
    }

    private void loadStudent(final String year, final String month, final String day) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ReportResponse> getStudent = apiService.getReportStudent(year, month, day);

        getStudent.enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(@NonNull Call<ReportResponse> call, @NonNull Response<ReportResponse> response) {
                studentList = new ArrayList<>();
                if  (response.body() != null) {
                    studentList = response.body().getData();
                    adapter = new FinalReportAdapter(studentList);
                    recyclerView.setAdapter(adapter);
                    hideLoading();
                }else {
                    hideLoading();
                    Toast.makeText(view.getContext(), "Data gagal dimuat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ReportResponse> call, @NonNull Throwable t) {

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
