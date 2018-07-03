package com.example.nishan.reelnepal.Facebook;



import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nishan.reelnepal.Movie.MovieProfile2;
import com.example.nishan.reelnepal.R;
import com.example.nishan.reelnepal.TestActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class FacebookActivity extends AppCompatActivity {

    private static final String TAG = FacebookActivity.class.getSimpleName();

    CallbackManager callbackManager;
    TextView txtEmail;
    ProgressDialog mDialog;
    ImageView imgAvatar;

    String FbEmail;

    String fbID;

    String rating;

    int movieID;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_facebook);

        Intent intent = getIntent();

        movieID = intent.getIntExtra("movieId", 0);
        Toast.makeText(getApplicationContext(),"Movie Id pass :"+movieID,Toast.LENGTH_LONG).show();

        rating = intent.getStringExtra("rating");
        Toast.makeText(getApplicationContext(),"Rating pass :"+rating,Toast.LENGTH_LONG).show();


        callbackManager = CallbackManager.Factory.create();
        Log.d(TAG,"callbackManager"+callbackManager);



       // txtEmail = findViewById(R.id.txtEmail);

        imgAvatar = findViewById(R.id.avatar);

        LoginButton loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mDialog = new ProgressDialog(FacebookActivity.this);
                mDialog.setMessage("Retriving data...");
                mDialog.show();

               // String accessToken = loginResult.getAccessToken().getToken();

                 GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        mDialog.dismiss();
                        Log.d(TAG,"response"+response.toString());

                        getData(object);
                       // Bundle facebookData = getFacebookData(object);

                       Intent i = new Intent(getApplicationContext(),TestActivity.class);
                      // i.putExtra("email", FbEmail);
                       i.putExtra("FbID",fbID);
                       i.putExtra("movieId",movieID);
                       i.putExtra("rating",rating);
                        startActivity(i);
                        Log.d(TAG,"movie ID:"+movieID);

                       /* //sending email to MovieProfile2 fragment
                        Bundle bundle = new Bundle();
                        bundle.putString("email",FbEmail);
                        Log.d(TAG, "email "+FbEmail);

                        MovieProfile2 fragobj = new MovieProfile2();
                        fragobj.setArguments(bundle);

                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.facebook, fragobj);
                        ft.commit();*/

                       // transaction.replace(R.id.fragment_single, fragInfo);
                        //transaction.commit();


                    }
                });

                //Request Graph API
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, email");
                request.setParameters(parameters);
                request.executeAsync();
                Log.d(TAG,"bundle response"+request.executeAsync());





            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        //if already login
        if (AccessToken.getCurrentAccessToken() !=null){
            //get user ID
           // txtEmail.setText(AccessToken.getCurrentAccessToken().getUserId());

            Intent i = new Intent(getApplicationContext(),TestActivity.class);
            i.putExtra("FbID",AccessToken.getCurrentAccessToken().getUserId());
            i.putExtra("movieId",movieID);
            i.putExtra("rating",rating);
            startActivity(i);


        }


        printKeyHash();

       // passEmailToMovieProfile2();
    }

 /*   private void passEmailToMovieProfile2() {
        Bundle bundle = new Bundle();
        bundle.putString("email",FbEmail);
        Log.d(TAG, "email "+FbEmail);
// set Fragmentclass Arguments
        MovieProfile2 fragobj = new MovieProfile2();
        fragobj.setArguments(bundle);
    }*/


    private void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.nishan.reelnepal", PackageManager.GET_SIGNATURES);
            for (Signature signature :info.signatures){
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d(TAG,"KeyHash"+ Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

   // private Bundle getFacebookData(){
     //   Bundle bundle = new Bundle();
   // }

    public void getData(JSONObject object){
        try{
            URL picture_pofile = new URL("https://graph.facebook.com/"+object.getString("id")+"/picture?width=250&height=250");
            Log.d(TAG,"URL response"+ new URL("https://graph.facebook.com/"+object.getString("id")+"/picture?width=250&height=250") );

            Picasso.with(this).load(picture_pofile.toString()).into(imgAvatar);

//            txtEmail.setText(object.getString("email"));

            FbEmail = object.getString("email");
           // Toast.makeText(FacebookActivity.this, "Email: "+FbEmail,Toast.LENGTH_LONG).show();

            fbID = object.getString("id");




          /*  SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("email", FbEmail);
            Log.d(TAG, "Email in Sp "+editor.putString("email", FbEmail));
            editor.commit();*/



        }
        catch (MalformedURLException e){

            e.printStackTrace();

        }
        catch (JSONException e){

            e.printStackTrace();

        }
    }
}
