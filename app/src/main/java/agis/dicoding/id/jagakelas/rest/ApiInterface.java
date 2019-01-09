package agis.dicoding.id.jagakelas.rest;

import agis.dicoding.id.jagakelas.model.AngkatanResponse;
import agis.dicoding.id.jagakelas.model.JagaResponse;
import agis.dicoding.id.jagakelas.model.JurusanResponse;
import agis.dicoding.id.jagakelas.model.KelasResponse;
import agis.dicoding.id.jagakelas.model.ReportResponse;
import agis.dicoding.id.jagakelas.model.SapuBersihRespone;
import agis.dicoding.id.jagakelas.model.StudentResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiInterface {
    @GET("/api/student/{angkatan}/{jurusan}/{kelas}")
    Call<StudentResponse> getStudent(@Path("angkatan") String angkatan, @Path("jurusan") String jurusan, @Path("kelas") String kelas);

    @GET("/api/angkatan")
    Call<AngkatanResponse> getAngkatan();

    @GET("/api/jurusan/{angkatan}")
    Call<JurusanResponse> getJurusan(@Path("angkatan") String angkatan);

    @GET("/api/kelas/{angkatan}/{jurusan}")
    Call<KelasResponse> getKelas(@Path("angkatan") String angkatan, @Path("jurusan") String jurusan);

    @FormUrlEncoded
    @POST("/api/jaga")
    Call<JagaResponse> insertJaga(@Field("nis") String nis, @Field("date") String date);

    @GET("/api/jaga/{nis}/{date}")
    Call<JagaResponse> getStatusJaga(@Path("nis") String nis, @Path("date") String date);

    @GET("/api/jaga/delete/{nis}/{date}")
    Call<JagaResponse> deletejaga(@Path("nis") String nis, @Path("date") String date);

    @GET("/api/jaga")
    Call<JagaResponse> getJaga();

    @GET("/api/jaga/date/{year}")
    Call<JagaResponse> getJagaMonth(@Path("year") String year);

    @GET("/api/jaga/date/{year}/{month}")
    Call<JagaResponse> getJagaDay(@Path("year") String year, @Path("month") String month);

    @GET("/api/jaga/student/{year}/{month}/{day}")
    Call<JagaResponse> getJagaStudent(@Path("year") String year, @Path("month") String month, @Path("day") String day);





    @FormUrlEncoded
    @POST("/api/sapubersih")
    Call<SapuBersihRespone> insertSapuBersih(@Field("nis") String nis, @Field("date") String date);

    @GET("/api/sapubersih/{nis}/{date}")
    Call<SapuBersihRespone> getStatusSapuBersih(@Path("nis") String nis, @Path("date") String date);

    @GET("/api/sapubersih/delete/{nis}/{date}")
    Call<SapuBersihRespone> deleteSapuBersih(@Path("nis") String nis, @Path("date") String date);

    @GET("/api/sapubersih/")
    Call<SapuBersihRespone> getSapuBersih();

    @GET("/api/sapubersih/date/{year}")
    Call<SapuBersihRespone> getSapuBersihMonth(@Path("year") String year);

    @GET("/api/sapubersih/date/{year}/{month}")
    Call<SapuBersihRespone> getSapuBersihDay(@Path("year") String year, @Path("month") String month);

    @GET("/api/sapubersih/student/{year}/{month}/{day}")
    Call<SapuBersihRespone> getSapuBersihStudent(@Path("year") String year, @Path("month") String month, @Path("day") String day);

    @GET("/api/report/")
    Call<ReportResponse> getReport();

    @GET("/api/report/date/{year}")
    Call<ReportResponse> getReportMonth(@Path("year") String year);

    @GET("/api/report/date/{year}/{month}")
    Call<ReportResponse> getReportDay(@Path("year") String year, @Path("month") String month);

    @GET("/api/report/student/{year}/{month}/{day}")
    Call<ReportResponse> getReportStudent(@Path("year") String year, @Path("month") String month, @Path("day") String day);
}
