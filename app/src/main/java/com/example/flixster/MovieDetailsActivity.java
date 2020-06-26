package com.example.flixster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.databinding.ActivityMovieDetailsBinding;
import com.example.flixster.databinding.ActivityMovieTrailerBinding;
import com.example.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import okhttp3.Headers;

public class MovieDetailsActivity extends AppCompatActivity implements ImageView.OnClickListener {

    // the movie to display
    Movie movie;

    // the view objects
    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbVoteAverage;
    ImageView backdrop;

    // Youtube Video key
    String videoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMovieDetailsBinding binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //setContentView(R.layout.activity_movie_details);
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        getVideoID(movie.getId());
        // resolve the view objects
        tvTitle = binding.tvTitle; //(TextView) findViewById(R.id.tvTitle);
        tvOverview = binding.tvOverview; //(TextView) findViewById(R.id.tvOverview);
        rbVoteAverage = binding.rbVoteAverage; //(RatingBar) findViewById(R.id.rbVoteAverage);
        backdrop = binding.ivBackdrop; //(ImageView) findViewById(R.id.ivBackdrop);
        // unwrap the movie passed in via intent, using its simple name as a key


        // set the title and overview
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

        // vote average is 0..10, convert to 0..5 by dividing by 2
        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage = voteAverage > 0 ? voteAverage / 2.0f : voteAverage);
        String imageUrl = movie.getBackdropPath();
        int placeholder = R.drawable.flicks_backdrop_placeholder;
        Glide.with(this).load(imageUrl).transform(new RoundedCorners(2)).placeholder(placeholder).into(backdrop);


    }

    // Get Key for youtube video from youtube api using movieId
    private void getVideoID(Integer movieId){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = String.format("https://api.themoviedb.org/3/movie/%d/videos?api_key=1d7e1b5d130a0ec5165440f04213bcc3&language=en-US", movieId);
        Log.i("myapp", url);
        Log.i("myapp", Integer.toString(movieId));
        // making a network request
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    Log.i("myapp", "Results: " + results.toString());
                    videoId = results.getJSONObject(0).getString("key");
                    Log.i("myapp", "key: " + videoId);
                } catch (JSONException e) {
                    Log.e("myapp", "Hit Json exception", e);
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.i("myapp", "onFailure", throwable);
            }
        });
    }
    @Override
    public void onClick(View view) {
        //Log.i("myapp", videoId);
        if(videoId!=null){
            Intent intent = new Intent(MovieDetailsActivity.this, MovieTrailerActivity.class);
            //Log.i("myapp", Integer.toString(movie.getId()));
            intent.putExtra("videoId", videoId);
            MovieDetailsActivity.this.startActivity(intent);
        }
    }
}
