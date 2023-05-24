package net.larntech.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;

import net.larntech.loginregister.models.LoginRequest;
import net.larntech.loginregister.models.LoginResponse;
import net.larntech.loginregister.retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    TextInputEditText username, password;
    Button btnLogin;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.edUsername);
        password = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(MainActivity.this,"Username / Password Required", Toast.LENGTH_LONG).show();
                }else{
                    login();
                }

            }
        });
   }


   public void login(){
      LoginRequest loginRequest = new LoginRequest();
      loginRequest.setUsername(username.getText().toString());
      loginRequest.setPassword(password.getText().toString());

      Call<LoginResponse> loginResponseCall = ApiClient.getUserService().login(loginRequest);
      loginResponseCall.enqueue(new Callback<LoginResponse>() {
          @Override
          public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

              if(response.isSuccessful()){
                  Toast.makeText(MainActivity.this,"Login Successful", Toast.LENGTH_LONG).show();
                  LoginResponse loginResponse = response.body();

                  new Handler().postDelayed(new Runnable() {
                      @Override
                      public void run() {

                          assert loginResponse != null;
                          if (loginResponse.isActive()){
                              for (String roles: loginResponse.getRoles()){
                                  if (roles.equals("ROLE_ADMIN")) {
                                      Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                                      intent.putExtra("username", loginResponse.getUsername());
                                      startActivity(intent);
                                  }
                                  if (roles.equals("ROLE_STUDENT")) {
                                      Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                                      intent.putExtra("username", loginResponse.getUsername());
                                      intent.putExtra("id", Integer.toString(loginResponse.getId()));
                                      intent.putExtra("token", loginResponse.getAccessToken());
                                      startActivity(intent);

                                  }
                                  if (roles.equals("ROLE_TEACHER")) {
                                      for (String roles2: loginResponse.getRoles()){
                                          if (roles2.equals("ROLE_STUDENT")) {
                                              Intent intent = new Intent(MainActivity.this, StudentTeacherActivity.class);
                                              intent.putExtra("username", loginResponse.getUsername());
                                              intent.putExtra("id", Integer.toString(loginResponse.getId()));
                                              intent.putExtra("token", loginResponse.getAccessToken());
                                              startActivity(intent);
                                              break;
                                          } else {
                                              Intent intent = new Intent(MainActivity.this, TeacherActivity.class);
                                              intent.putExtra("username", loginResponse.getUsername());
                                              intent.putExtra("id", Integer.toString(loginResponse.getId()));
                                              intent.putExtra("token", loginResponse.getAccessToken());
                                              startActivity(intent);
                                          }
                                      }
                                  }
                              }
                          } else Toast.makeText(MainActivity.this,"Аккаунт заблокирован", Toast.LENGTH_LONG).show();

                      }
                  },700);

              }else{
                  Toast.makeText(MainActivity.this,"Login Failed", Toast.LENGTH_LONG).show();

              }

          }

          @Override
          public void onFailure(Call<LoginResponse> call, Throwable t) {
              Toast.makeText(MainActivity.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

          }
      });


   }

}
