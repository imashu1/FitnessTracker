package pratiksha.example.myfiitnessapplication.fragments;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

import pratiksha.example.myfiitnessapplication.R;
import pratiksha.example.myfiitnessapplication.activities.MainActivity;
import pratiksha.example.myfiitnessapplication.model.User;
import pratiksha.example.myfiitnessapplication.services.MyInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {


    Spinner genderInput_reg,professionalSpinner;
    EditText nameInput_reg,emailInput_reg,phoneInput_reg,passwordInput_reg,dob_reg,editTextotp;


    Button button_reg,buttonConfirm;
    MyInterface myInterface_reg;
    DatePickerDialog picker;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_register, container, false);
        nameInput_reg=view.findViewById(R.id.nameInput);
        emailInput_reg=view.findViewById(R.id.emailInput);
        phoneInput_reg=view.findViewById(R.id.phoneInput);
        passwordInput_reg=view.findViewById(R.id.passwordInput);

        genderInput_reg = (Spinner) view.findViewById(R.id.genderInput);
        String [] usertype =
                {"Male","Female"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, usertype);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        genderInput_reg.setAdapter(adapter);
        dob_reg=view.findViewById(R.id.dobInput);
        dob_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dob_reg.setText( year+ "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        professionalSpinner=(Spinner)view.findViewById(R.id.professionalSpinner);
        String [] usertype1 =
                {"GimTrainer","YogaTrainer","Doctor","Psychologist","DietTrainer"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, usertype1);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        professionalSpinner.setAdapter(adapter1);
        button_reg=view.findViewById(R.id.regBtn);
        button_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }

            private void registerUser() {
                String name=nameInput_reg.getText().toString().trim();
                String email=emailInput_reg.getText().toString().trim();
                String phone=phoneInput_reg.getText().toString().trim();
                String password=passwordInput_reg.getText().toString().trim();
                String gender1 = genderInput_reg.getSelectedItem().toString();
                String dob=dob_reg.getText().toString().trim();
                String profession=professionalSpinner.getSelectedItem().toString().trim();





                if(TextUtils.isEmpty(name)){
                   // MainActivity.appPreferences.showToast("Enter your name");
                    Snackbar.make(nameInput_reg,"EnterYourName",Snackbar.LENGTH_LONG)
                            .setBackgroundTint(Color.parseColor("#006400"))
                            .setActionTextColor(Color.parseColor("red"))
                    .setAction("Retry", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Toast.makeText(getActivity(), "retry", Toast.LENGTH_SHORT).show();
                            clearText1();
                        }

                        private void clearText1() {
                            emailInput_reg.setText("");
                            nameInput_reg.setText("");

                            passwordInput_reg.setText("");
                            phoneInput_reg.setText("");
                        }
                    }).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                   // MainActivity.appPreferences.showToast("Your email is invalid");
                    Snackbar.make(nameInput_reg,"Your email is invalid",Snackbar.LENGTH_LONG)
                            .setBackgroundTint(Color.parseColor("#006400"))
                            .setActionTextColor(Color.parseColor("red"))
                            .setAction("Retry", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Toast.makeText(getActivity(), "retry", Toast.LENGTH_SHORT).show();
                                    clearText1();
                                }

                                private void clearText1() {
                                    emailInput_reg.setText("");
                                    nameInput_reg.setText("");

                                    passwordInput_reg.setText("");
                                    phoneInput_reg.setText("");
                                }
                            }).show();
                }
                else if(TextUtils.isEmpty(gender1)){
                   // MainActivity.appPreferences.showToast("Enter your gender");
                    Snackbar.make(nameInput_reg,"Enter your gender/oi",Snackbar.LENGTH_LONG)
                            .setBackgroundTint(Color.parseColor("#006400"))
                            .setActionTextColor(Color.parseColor("red"))
                            .setAction("Retry", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Toast.makeText(getActivity(), "retry", Toast.LENGTH_SHORT).show();
                                    clearText1();
                                }

                                private void clearText1() {
                                    emailInput_reg.setText("");
                                    nameInput_reg.setText("");

                                    passwordInput_reg.setText("");
                                    phoneInput_reg.setText("");
                                }
                            }).show();
                }
                else if(TextUtils.isEmpty(dob)){
                   // MainActivity.appPreferences.showToast("Enter your DOb");
                    Snackbar.make(nameInput_reg,"Enter your DOB",Snackbar.LENGTH_LONG)
                            .setBackgroundTint(Color.parseColor("#006400"))
                            .setActionTextColor(Color.parseColor("red"))

                            .setAction("Retry", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Toast.makeText(getActivity(), "retry", Toast.LENGTH_SHORT).show();
                                    clearText1();
                                }

                                private void clearText1() {
                                    emailInput_reg.setText("");
                                    nameInput_reg.setText("");

                                    passwordInput_reg.setText("");
                                    phoneInput_reg.setText("");
                                }
                            }).show();
                } else if(TextUtils.isEmpty(phone)){
                   // MainActivity.appPreferences.showToast("Enter your phone");
                    Snackbar.make(nameInput_reg,"Enter your phone",Snackbar.LENGTH_LONG)
                            .setBackgroundTint(Color.parseColor("#006400"))
                            .setActionTextColor(Color.parseColor("red"))
                            .setAction("Retry", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Toast.makeText(getActivity(), "retry", Toast.LENGTH_SHORT).show();
                                    clearText1();
                                }

                                private void clearText1() {
                                    emailInput_reg.setText("");
                                    nameInput_reg.setText("");

                                    passwordInput_reg.setText("");
                                    phoneInput_reg.setText("");
                                }
                            }).show();
                } else if (phone.length()>10 || phone.length()<10){

                  //  MainActivity.appPreferences.showToast("Enter correct no");
                    Snackbar.make(nameInput_reg,"Enter correct no",Snackbar.LENGTH_LONG)
                            .setBackgroundTint(Color.parseColor("#006400"))
                            .setActionTextColor(Color.parseColor("red"))
                            .setAction("Retry", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Toast.makeText(getActivity(), "retry", Toast.LENGTH_SHORT).show();
                                    clearText1();
                                }

                                private void clearText1() {
                                    emailInput_reg.setText("");
                                    nameInput_reg.setText("");

                                    passwordInput_reg.setText("");
                                    phoneInput_reg.setText("");
                                }
                            }).show();
                }
                else if(TextUtils.isEmpty(password)){
                    //MainActivity.appPreferences.showToast("Enter your pass");
                    Snackbar.make(nameInput_reg,"Enter your pass",Snackbar.LENGTH_LONG)
                            .setBackgroundTint(Color.parseColor("#006400"))
                            .setActionTextColor(Color.parseColor("red"))
                            .setAction("Retry", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Toast.makeText(getActivity(), "retry", Toast.LENGTH_SHORT).show();
                                    clearText1();
                                }

                                private void clearText1() {
                                    emailInput_reg.setText("");
                                    nameInput_reg.setText("");

                                    passwordInput_reg.setText("");
                                    phoneInput_reg.setText("");
                                }
                            }).show();
                }  else if(password.length()<6)
                {
                 //   MainActivity.appPreferences.showToast("your email length will not match to patern");
                    Snackbar.make(nameInput_reg,"your email length will not match to patern",Snackbar.LENGTH_LONG)
                            .setBackgroundTint(Color.parseColor("#006400"))
                            .setActionTextColor(Color.parseColor("red"))
                            .setAction("Retry", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Toast.makeText(getActivity(), "retry", Toast.LENGTH_SHORT).show();
                                    clearText1();
                                }

                                private void clearText1() {
                                    emailInput_reg.setText("");
                                    nameInput_reg.setText("");

                                    passwordInput_reg.setText("");
                                    phoneInput_reg.setText("");
                                }
                            }).show();
                }

                else
                {
                    Call<User> userCall=MainActivity.serviceApi.doRegisteration(name,email,gender1,dob,password,phone,profession);
                    userCall.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(@NonNull Call<User> call,@NonNull Response<User> response) {

                            if(response.body().getResponse().matches("inserted"))
                            {
                                show_Message("Welcome "+name," Check Mail to Activate Account");

                                // Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                            }else if(response.body().getResponse().matches("exists"))
                            {
                                show_Message("Already registered user!!","Welcome "+name);
                                //  Toast.makeText(getActivity(), "User already exists!!!!", Toast.LENGTH_SHORT).show();
                            }

                            Log.i("My response",response.body().getResponse());
                        }

                        private void show_Message(String title, String input) {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle(title);
                            builder.setMessage(input);
                            final View customLayout = getLayoutInflater().inflate(R.layout.dialog_verify, null);
                            builder.setView(customLayout);
                            buttonConfirm =  customLayout.findViewById(R.id.buttonConfirm);
                            editTextotp = customLayout.findViewById(R.id.editTextOtp);
                            buttonConfirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String otp =editTextotp.getText().toString();
                                    Call<User> userCall = MainActivity.serviceApi.doverify(otp);
                                    userCall.enqueue(new Callback<User>() {
                                        @Override
                                        public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                                            if(response.body().getResponse().equals("correct"))
                                            {
                                                MainActivity.appPreferences.setLoginStatus(true);
                                                myInterface_reg.logout();
                                                Toast.makeText(getActivity(), "verify  successfull", Toast.LENGTH_SHORT).show();
                                            }
                                            else if(response.body().getResponse().equals("incorrect"))
                                            {
                                                Toast.makeText(getActivity(), "wrong otp", Toast.LENGTH_SHORT).show();
                                                editTextotp.setText("");

                                            }

                                        }

                                        @Override
                                        public void onFailure(Call<User> call, Throwable t) {
                                            System.out.println("myerror" + t.getMessage());
                                            Toast.makeText(getActivity(), "failure", Toast.LENGTH_SHORT).show();

                                        }
                                    });




                                }
                            });

                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {




                                }
                            });
                            builder.show();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            System.out.println("myerror"+t.getMessage());
                            Toast.makeText(getActivity(), "failure", Toast.LENGTH_SHORT).show();

                        }
                    });



                }




            }
        });


        return  view;
    }

    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);
        Activity activity= (Activity) context;
        myInterface_reg= (MyInterface) activity;
    }
}