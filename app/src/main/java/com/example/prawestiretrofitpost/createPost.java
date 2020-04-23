package com.example.prawestiretrofitpost;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class createPost extends AppCompatActivity {
    private EditText inputId, inputNama, inputAlamat, inputTelp;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Button btn = findViewById(R.id.btn_tambah);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputId = findViewById(R.id.edt_Id);
                inputNama = findViewById(R.id.edt_nama);
                inputAlamat = findViewById(R.id.edt_alamat);
                inputTelp = findViewById(R.id.edt_telp);

                //untuk menyimpan data dari dat ayng dimasukkan dari form
                String nim = inputId.getText().toString();
                String nama = inputNama.getText().toString();
                String alamat = inputAlamat.getText().toString();
                radioGroup = findViewById(R.id.group_jk);
                RadioButton selected = findViewById(radioGroup.getCheckedRadioButtonId());
                String jenis_kelamin = selected.getText().toString();
                String no_telp = inputTelp.getText().toString();

                //eror handling
                if (TextUtils.isEmpty(nim) || TextUtils.isEmpty(nama) || TextUtils.isEmpty(alamat)
                        || TextUtils.isEmpty(jenis_kelamin) || TextUtils.isEmpty(no_telp)) {
                    Toast.makeText(getApplicationContext(), "Fill the field", Toast.LENGTH_SHORT).show();
                } else {
                    Post post = new Post(nim, nama, alamat, jenis_kelamin, no_telp);

                    //jika tidak kosong, maka retrofit akan diinstansiasi ke base url
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.43.47/mahasiswa/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

                    //kemudian mengisi parameternya ke body sesuai data yang dibutuhkan
                    Call<ResponseBody> call = jsonPlaceHolderApi.createPost(post);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }
}

