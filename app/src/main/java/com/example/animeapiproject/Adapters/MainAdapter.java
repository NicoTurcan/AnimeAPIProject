package com.example.animeapiproject.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animeapiproject.Listeners.OnAnimeClick;
import com.example.animeapiproject.Models.ItemObject;
import com.example.animeapiproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder>{

    //gets list of items that will show the image, title, and keep id for later
    Context context;
    List<ItemObject> listOfItems;
    OnAnimeClick onClickListener; // using onclicklistener

    public MainAdapter(Context context, List<ItemObject> listOfItems, OnAnimeClick onClickListener) {
        this.context = context;
        this.listOfItems = listOfItems;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_anime_list,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        Picasso.get().load(listOfItems.get(position).getImage().getJpg().getImage_url()).into(holder.animeImgHolder); // using picasso to place image
        holder.animeTxtHolder.setSelected(true); // for the infinite scrolling
        holder.animeTxtHolder.setText(listOfItems.get(position).getTitle()); // set title

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onAnimeClick(listOfItems.get(position).getMal_id()); // using on click listener, get id for later
            }

        });

    }

    @Override
    public int getItemCount() {
        if(listOfItems==null){
            return 0; // for safety and debugging
        }
        else{
            return listOfItems.size();
        }
    }

}

//holder class in here as well
class MainViewHolder extends RecyclerView.ViewHolder{

    // the cardview its in, the img and the title
    CardView card;
    ImageView animeImgHolder;
    TextView animeTxtHolder;

    public MainViewHolder(@NonNull View itemView) {
        super(itemView);

        // set all of them so we can actually use the holder as intended
        animeImgHolder = itemView.findViewById(R.id.animePoster);
        animeTxtHolder = itemView.findViewById(R.id.movieTitleView);
        card = itemView.findViewById(R.id.container_anime);

    }
}
