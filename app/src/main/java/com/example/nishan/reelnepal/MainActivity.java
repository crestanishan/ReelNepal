package com.example.nishan.reelnepal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nishan.reelnepal.Actor.ActorProfileActivity;
import com.example.nishan.reelnepal.Adapters.ActorListAdapter;
import com.example.nishan.reelnepal.Facebook.FacebookActivity;
import com.example.nishan.reelnepal.Facebook.Test;
import com.example.nishan.reelnepal.Interface.ApiInterface;
import com.example.nishan.reelnepal.Movie.MovieProfileActivity;
import com.example.nishan.reelnepal.Navigation.Home_Nav.Home;
import com.example.nishan.reelnepal.Navigation.MovieCalender_Nav.MovieCalender;
import com.example.nishan.reelnepal.Navigation.NepaliNews_Nav.NepaliNews;
import com.example.nishan.reelnepal.Navigation.News_Nav.News;
import com.example.nishan.reelnepal.Navigation.Videos_Nav.Videos;
import com.example.nishan.reelnepal.Search.Actor;
import com.example.nishan.reelnepal.Search.ApiClient;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        ActorListAdapter.OnActorItemClickInterface

{

    private static final String TAG = MainActivity.class.getSimpleName();

    ActorListAdapter.OnActorItemClickInterface onActorItemClickInterface;

    MaterialSearchView searchView;

    private ListView listViewActors;

    List<Actor> actors;

    ApiInterface apiInterface;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        searchViewCode();

        listViewActors = findViewById(R.id.listViewActor);

        onActorItemClickInterface = this;

        apiInterface  = ApiClient.getClient().create(ApiInterface.class);

       // FacebookSdk.sdkInitialize(getApplicationContext());
        //CallbackManager callbackManager = CallbackManager.Factory.create();




       // FacebookActivity facebookActivity = new FacebookActivity();





     //  Toast.makeText(getApplicationContext(),"FbID: "+Test.Id,Toast.LENGTH_LONG).show();




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // define navigation headers
        TextView fbName =  navigationView.getHeaderView(0).findViewById(R.id.fb_name);
        TextView fbEmail =  navigationView.getHeaderView(0).findViewById(R.id.fb_email);
        ImageView fbPic = navigationView.getHeaderView(0).findViewById(R.id.fb_pic);

        LinearLayout layout = findViewById(R.id.nav_header);

        //getting data of fb through Test class
        String fbID = Test.Id;
        String facebookEmail = Test.Email;
        String facebookName = Test.Name;

       /* String fbID = prefs.getString("fbID", "default value");
        String facebookEmail = prefs.getString("fbEmail", "default value");
        String facebookName = prefs.getString("fbName", "default value");*/


        Toast.makeText(getApplicationContext(),"FbID: "+fbID,Toast.LENGTH_LONG).show();

        Picasso.with(this).load( "https://graph.facebook.com/"+fbID+"/picture?width=200&height=200").into(fbPic);

        fbEmail.setText(facebookEmail);

        fbName.setText(facebookName);

     


        displaySelectedScreen(R.id.nav_home);
    }

            private void searchViewCode() {

                searchView = findViewById(R.id.search_view);
                // searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
                searchView.setEllipsize(true);
                searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {

                        Log.d(TAG, "onQueryTextSubmit: "+query);
                        return false;

                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        query(newText);
                        return false;
                    }
                });

                searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
                    @Override
                    public void onSearchViewShown() {

                    }

                    @Override
                    public void onSearchViewClosed() {

                    }
                });

            }

            private void query(String text) {
                apiInterface.queryActor(text).enqueue(new Callback<List<Actor>>() {
                    @Override
                    public void onResponse(Call<List<Actor>> call, Response<List<Actor>> response) {
                        Log.d(TAG, "onResponse:Server Response: "+ response.toString());
                        Log.d(TAG, "onResponse: "+ response.body().size());
                        // Toast.makeText(getApplicationContext(),"Output:"+response.body(),Toast.LENGTH_LONG).show();
                        actors = response.body();
                        ActorListAdapter actorListAdapter = new ActorListAdapter(getApplicationContext(), actors, onActorItemClickInterface);
                        listViewActors.setAdapter(actorListAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Actor>> call, Throwable t) {
                        Log.d(TAG, "onFailure: "+t.getMessage());
                    }
                });
            }


            @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

                if (searchView.isSearchOpen()){
                    searchView.closeSearch();
                }
                else {
                    super.onBackPressed();
                }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        if (id == R.id.action_sign_in) {
            Intent i = new Intent(this,FacebookActivity.class);
            this.startActivity(i);
            return true;

        }

        return super.onOptionsItemSelected(item);


    }

    private void displaySelectedScreen(int id){
        Fragment fragment = null;

        switch (id){
            case R.id.nav_home:
                fragment = new Home();
            break;

            case R.id.nav_news:
                fragment = new News();
                break;

            case R.id.nav_nepali_news:
                fragment = new NepaliNews();
                break;

            case R.id.nav_videos:
                fragment = new Videos();
                break;

            case R.id.nav_movie_calender:
                fragment = new MovieCalender();
                break;


        }

        if (fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.contain_main, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        displaySelectedScreen(id);

        return true;
    }

    @Override
    public void onItemClick(Actor actor) {

        if (actor.getCategory().matches("mv")){

           /* Intent intent = new Intent(MainActivity.this, MovieProfileActivity.class);
            intent.putExtra("ID", actor.getId());
            startActivity(intent);*/


           /* MovieProfileActivity fragobj = new MovieProfileActivity();

            // pass arguments to fragment
            Bundle bundle = new Bundle();
            // the description of the row
            bundle.putSerializable("ID",actor.getId() );

            fragobj.setArguments(bundle);*/
           Log.d(TAG,"Actor"+actor);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            ft.replace(R.id.contain_main,MovieProfileActivity.newInstance(actor));
            ft.commit();


//            getIntent().putExtra("Object",actor);

        }

        else {

            Intent intent = new Intent(MainActivity.this, ActorProfileActivity.class);
            startActivity(intent);

        }

    }


}
