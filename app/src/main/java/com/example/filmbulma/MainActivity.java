package com.example.filmbulma;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText movie;
    private TextView directorTV,titleTV, ratingTV, releasedTV, runtimeTV, genreTV, actorTV, plotTV;
    private ImageView posterIV;
    private Button searchBTN;
    private String jsonCevabi="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movie = findViewById(R.id.movie);
        directorTV = findViewById(R.id.director);
        titleTV = findViewById(R.id.title);
        ratingTV = findViewById(R.id.rating);
        releasedTV = findViewById(R.id.released);
        runtimeTV = findViewById(R.id.runtime);
        genreTV = findViewById(R.id.genre);
        actorTV = findViewById(R.id.actor);
        plotTV = findViewById(R.id.plot);
        posterIV = findViewById(R.id.poster);
        searchBTN = findViewById(R.id.search_btn);
    }
    private void apiTalep(String url) {

        RequestQueue talepSirasi = Volley.newRequestQueue(this);
        StringRequest talep = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("test", "Tüm cevap :" + response);
                jsonCevabi = response;
                jsonAyikla(); // jsonCevabi elde edildikten sonra json ayıklama işlemi(json parsing)
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("test", "Hata mesajı :" + error.toString());
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                //Futbol için
                //   params.put("X-RapidAPI-Key", "04baa94107msha408ed5dc80d980p1c4c48jsn3ded83c11c33");
                //   params.put("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com");
                //Covid için alttaki iki satırı yorum satırından kod satırı haline getiriniz (// ifadelerini kaldırınız)
                //   params.put("X-RapidAPI-Key", "04baa94107msha408ed5dc80d980p1c4c48jsn3ded83c11c33");
                //   params.put("X-RapidAPI-Host", "covid-193.p.rapidapi.com");
                //NBA için alttaki iki satırı yorum satırından kod satırı haline getiriniz (// ifadelerini kaldırınız)
                //   params.put("X-RapidAPI-Key", "04baa94107msha408ed5dc80d980p1c4c48jsn3ded83c11c33");
                //   params.put("X-RapidAPI-Host", "free-nba.p.rapidapi.com");
                return params;
            }
        };
        talepSirasi.add(talep);
    }

    private void jsonAyikla() {
        try {
            JSONObject joKOK = new JSONObject(jsonCevabi);
            String title = joKOK.getString("Title");
            String rated = joKOK.getString("Rated");
            String released = joKOK.getString("Released");
            String runtime = joKOK.getString("Runtime");
            String genre = joKOK.getString("Genre");
            String director = joKOK.getString("Director");
            String actors = joKOK.getString("Actors");
            String plot = joKOK.getString("Plot");
            String poster = joKOK.getString("Poster");
            titleTV.setText(title);
            ratingTV.setText("Rated : "+rated);
            releasedTV.setText(released);
            runtimeTV.setText(runtime);
            genreTV.setText(genre);
            directorTV.setText("Director : "+director);
            actorTV.setText("Actor"+ actors);
            plotTV.setText("Plot : "+plot);
            if(poster.equals("N/A")) {
            }
            else{
                Picasso.get().load(poster).into(posterIV);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void search(View view) {
        String movieName = movie.getText().toString();
        if(movieName.isEmpty()){
            movie.setError("Lütfen bir film adı giriniz");
            return;
        }
        String url = "http://www.omdbapi.com/?t=" + movieName + "&apikey=d38b4130";
        apiTalep(url);
    }
}