package com.pfa.cameraupload;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {
    @GET("webapi/enseignant/Seances/All")
    Call<ArrayList<Seance>> getSeancesAll(@Header("Authorization") String authKey);
    @GET("webapi/enseignant/Seances/")
    Call<ArrayList<Seance>> getSeances(@Header("Authorization") String authKey);
    @GET("webapi/enseignant/Seances/{idSeance}/Etudiants")
    Call<ArrayList<SeanceEtuds>> getEtudiantsAll(@Header("Authorization") String authKey,@Path("idSeance") Long idSeance);
    @GET("webapi/enseignant/Seances/{idSeance}/Absence")
    Call<ArrayList<SeanceEtuds>> getEtudiantsAbs(@Header("Authorization") String authKey,@Path("idSeance") Long idSeance);
    @POST("webapi/enseignant/Seances/Absence/Add")
    Call<SeanceAbsence> AddAbsence(@Header("Authorization") String authKey,@Body SeanceAbsence seanceAbsence);
    @POST("webapi/enseignant/Seances/Absence/Remove")
    Call<SeanceAbsence> RemoveAbsence(@Header("Authorization") String authKey,@Body SeanceAbsence seanceAbsence);
    @Multipart
    @POST("webapi/enseignant/upload")
    Call<ArrayList<SeanceEtudsFac>> upload(@Header("Authorization") String authKey,@Part MultipartBody.Part file,@Part("idSeance") RequestBody idSeance);
    @FormUrlEncoded
    @POST("webapi/login")
    Call<token> getToken(@Field("username") String username,@Field("password") String password);
    @GET("webapi/etudiant/Seances/All")
    Call<ArrayList<Seance>> getSeancesEtAll(@Header("Authorization") String authKey);
    @GET("webapi/etudiant/Seances/")
    Call<ArrayList<Seance>> getSeancesEt(@Header("Authorization") String authKey);
    @GET("webapi/etudiant/Seances/Absence")
    Call<ArrayList<Seance>> getSeancesEtAbs(@Header("Authorization") String authKey);
}
