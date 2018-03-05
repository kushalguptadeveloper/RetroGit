package com.example.kushalgupta.retrogit;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kushalgupta.retrogit.Model.AccessToken;
import com.example.kushalgupta.retrogit.interfaces.GitHubClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public int j=1;
private String ClientID="864005b887ce24808fec";
private String ClientSecret="53361d192496390469d7319a791d32c5ae5fb02d";
//private String redirectUrl="https://retrogit-203bc.firebaseapp.com/__/auth/handler";
  private String  redirectUrl="myapp://callback";
   // private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


//        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login/oauth/authorize"+"?client_id="+ClientID+"&scope=repo&redirect_uri="+redirectUrl));
  startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Uri uri=getIntent().getData();
        if(uri!=null && uri.toString().startsWith(redirectUrl)){
            String code=uri.getQueryParameter("code");

            Retrofit.Builder builder=new Retrofit.Builder().baseUrl("https://github.com/").addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit=builder.build();
            GitHubClient client=retrofit.create(GitHubClient.class);

          Call<AccessToken> accessTokenCall= client.getAccessToken(ClientID,ClientSecret,code);
accessTokenCall.enqueue(new Callback<AccessToken>() {
    @Override
    public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {

        if(j==1){
        Toast.makeText(MainActivity.this, "got token", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(MainActivity.this,Main2Activity.class);
        startActivity(i);
        j=0;
        }

    }

    @Override
    public void onFailure(Call<AccessToken> call, Throwable t) {
        Toast.makeText(MainActivity.this, " not got token", Toast.LENGTH_SHORT).show();

    }
});


        }
    }


}
/*
android:scheme="https"
        android:host="retrogit-203bc.firebaseapp.com"
        android:path="/__/auth/handler"/>*/
