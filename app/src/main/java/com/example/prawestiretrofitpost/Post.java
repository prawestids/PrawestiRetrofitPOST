package com.example.prawestiretrofitpost;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

//turunan dari kelas AbstractItem
public class Post extends AbstractItem<Post, Post.ViewHolder> {
    //deklarasi variabel
    private String id_siswa;
    private String nama;
    private String alamat;
    private String jenis_kelamin;
    private String no_telp;

    //konstruktor
    public Post(String id_siswa, String nama, String alamat, String jenis_kelamin, String no_telp){
        this.id_siswa = id_siswa;
        this.nama = nama;
        this.alamat = alamat;
        this.jenis_kelamin = jenis_kelamin;
        this.no_telp = no_telp;
    }

    //generate getter
    public String getId_siswa() {

        return id_siswa;
    }

    public String getNama() {

        return nama;
    }

    public String getAlamat() {

        return alamat;
    }

    public String getJenis_kelamin() {

        return jenis_kelamin;
    }

    public String getNo_telp() {

        return no_telp;
    }

    @NonNull
    @Override
    public ViewHolder getViewHolder(View v) {

        return new ViewHolder(v);
    }

    @Override
    public int getType() {

        return R.id.rv_mahasiswa;
    }

    @Override
    public int getLayoutRes() {

        return R.layout.item_siswa;
    }

    //class ViewHolder
    public class ViewHolder extends FastAdapter.ViewHolder<Post> {
        TextView TextId, TextNama, TextAlamat, TextJK, TextNo;

        public ViewHolder(View itemView) {
            super(itemView);
            TextId = itemView.findViewById(R.id.tv_id_mahasiswa);
            TextNama = itemView.findViewById(R.id.tv_nama);
            TextAlamat = itemView.findViewById(R.id.tv_alamat);
            TextJK = itemView.findViewById(R.id.tv_jenis_kelamin);
            TextNo = itemView.findViewById(R.id.tv_no_telp);
        }

        @Override
        public void bindView(Post item, List<Object> payloads) {
            TextId.setText(item.id_siswa);
            TextNama.setText(item.nama);
            TextAlamat.setText(item.alamat);
            TextJK.setText(item.jenis_kelamin);
            TextNo.setText(item.no_telp);
        }

        @Override
        public void unbindView(Post item) {
            TextId.setText(null);
            TextNama.setText(null);
            TextAlamat.setText(null);
            TextJK.setText(null);
            TextNo.setText(null);
        }
    }
}
