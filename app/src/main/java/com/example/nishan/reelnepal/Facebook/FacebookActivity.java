package com.example.nishan.reelnepal.Facebook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nishan.reelnepal.R;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_facebook);

        callbackManager = CallbackManager.Factory.create();
        Log.d(TAG,"callbackManager"+callbackManager);


        txtEmail = findViewById(R.id.txtEmail);

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

                final GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        mDialog.dismiss();
                        Log.d(TAG,"response"+response.toString());

                        getData(object);
                       // Bundle facebookData = getFacebookData(object);
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
            txtEmail.setText(AccessToken.getCurrentAccessToken().getUserId());
        }

        
        printKeyHash();
    }

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

            txtEmail.setText(object.getString("email"));
        }
        catch (MalformedURLException e){

            e.printStackTrace();

        }
        catch (JSONException e){

            e.printStackTrace();

        }
    }
}
