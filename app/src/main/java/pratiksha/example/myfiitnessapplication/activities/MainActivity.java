package pratiksha.example.myfiitnessapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import pratiksha.example.myfiitnessapplication.R;
import pratiksha.example.myfiitnessapplication.canstants.Constants;
import pratiksha.example.myfiitnessapplication.extras.AppPreferences;
import pratiksha.example.myfiitnessapplication.fragments.RegisterFragment;
import pratiksha.example.myfiitnessapplication.fragments.loginFragment;
import pratiksha.example.myfiitnessapplication.services.MyInterface;
import pratiksha.example.myfiitnessapplication.services.RetrofitClient;
import pratiksha.example.myfiitnessapplication.services.ServiceApi;

public class MainActivity extends AppCompatActivity implements MyInterface {
    public static AppPreferences appPreferences;
    public static ServiceApi serviceApi;
    FrameLayout container_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container_fragment=findViewById(R.id.fragment_container);
        appPreferences=new AppPreferences(this);
        serviceApi = RetrofitClient.getApiClient(Constants.baseUrl.Base_URL).create(ServiceApi.class);
        if(container_fragment!=null)
        {
            if(savedInstanceState!=null)
            {
                return;
            }
            if(appPreferences.getLoginStatus())
            {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new loginFragment()).commit();

            }
            else{
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new loginFragment()).commit();

            }
        }




    }

    @Override
    public void register() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new RegisterFragment())
                .addToBackStack(null)
                .commit();


    }

    @Override
    public void login(String name, String email, String created_at) {
        appPreferences.setDiaplayEmail(email);
        appPreferences.setDiaplayName(name);
        appPreferences.setDiaplayDate(created_at);



        Intent intent=new Intent(this, Home.class);
        startActivity(intent);


        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment())
        // .addToBackStack(null)
        //.commit();

    }

    @Override
    public void logout() {
        appPreferences.setLoginStatus(false);
        appPreferences.setDiaplayName("Name");
        appPreferences.setDiaplayEmail("Email");

        appPreferences.setDiaplayDate("Date");

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new loginFragment())
                .addToBackStack(null)
                .commit();

    }
    @Override
    public void verify() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new loginFragment())
                .addToBackStack(null)
                .commit();
    }
}