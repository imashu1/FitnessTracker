package pratiksha.example.myfiitnessapplication.services;



import pratiksha.example.myfiitnessapplication.model.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceApi {

    @POST("regiform.php")
    @FormUrlEncoded
    Call<User> doRegisteration(
            @Field("name") String name,
            @Field("email") String email,
            @Field("gender") String gender,
            @Field("dob") String dob,
            @Field("password") String password,
            @Field("phone") String phone,
            @Field("profession") String profession
    );

    @POST("login.php")
    @FormUrlEncoded
    Call<User> doLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("emailv.php")
    Call<User> doverify(
            @Query("otp") String otp

    );
}
