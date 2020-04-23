package com.example.prawestiretrofitpost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        untuk menampilkan judul
        textViewResult = findViewById(R.id.judul);

        final RecyclerView mahasiswaView = findViewById(R.id.rv_mahasiswa);
        final ItemAdapter itemAdapter = new ItemAdapter<>();
        final FastAdapter fastAdapter = FastAdapter.with(itemAdapter);

        final List mahasiswa = new ArrayList<>();

        // memanggil rest_api dengan base_url
        // disini saya memakai localhost yang berada di folder mahasiswa
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.47/mahasiswa/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Post>> call = jsonPlaceHolderApi.getPost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code " + response.code());
                    return;
                }
                List<Post> posts = response.body();
                for (Post post : posts) {
                    mahasiswa.add(new Post(
                            post.getId_siswa(),
                            post.getNama(),
                            post.getAlamat(),
                            post.getJenis_kelamin(),
                            post.getNo_telp()));
                }
                itemAdapter.add(mahasiswa);
                mahasiswaView.setAdapter(fastAdapter);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                mahasiswaView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
    //kode untuk berpindah ke halaman tambah data siswa
    public void myOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), createPost.class);
        startActivity(intent);
    }
}

