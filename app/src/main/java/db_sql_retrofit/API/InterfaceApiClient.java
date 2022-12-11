package db_sql_retrofit.API;


import java.util.ArrayList;

import db_sql_retrofit.MainComponents.Model;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceApiClient {

    @GET("DB_SQL_Retrofit_GET.php")
    Call<ArrayList<Model>> getModel(
            @Query("Table") String Table,
            @Query("Column") String Column,
            @Query("Name") String Name
    );
}