package com.shinmusic.shinmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.shinmusic.R;
import com.shinmusic.shinmusic.Model.Album;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.Viewholder>{

    Context context;
    ArrayList<Album> mangalbum;

    public AlbumAdapter(Context context, ArrayList<Album> mangalbum) {
        this.context = context;
        this.mangalbum = mangalbum;
    }

    @Override
    public Viewholder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        Album album = mangalbum.get(position);
        holder.txtcasyalbum.setText(album.getTenCaSy());
        holder.txttenalbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getImageAlbum()).into(holder.imghinhalbum);
    }

    @Override
    public int getItemCount() {
        return mangalbum.size();
    }

    public  class  Viewholder extends RecyclerView.ViewHolder{
        ImageView imghinhalbum;
        TextView txttenalbum,txtcasyalbum;
        public Viewholder(View itemView) {
            super(itemView);
            imghinhalbum = itemView.findViewById(R.id.imageviewalbum);
            txttenalbum = itemView.findViewById(R.id.textviewtenalbum);
            txtcasyalbum = itemView.findViewById(R.id.textviewtencasyalbum);

        }
    }
}
