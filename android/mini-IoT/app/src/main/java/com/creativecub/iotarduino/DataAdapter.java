package com.creativecub.iotarduino;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
 
import java.util.List;
 
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
 
    private List<Data> moviesList;
 
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
        public ImageView ivIcon;
        public Switch aSwitch;
 
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
            ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
            aSwitch = (Switch) view.findViewById(R.id.switch1);

        }
    }
 
 
    public DataAdapter(List<Data> moviesList) {
        this.moviesList = moviesList;
    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_row, parent, false);
 
        return new MyViewHolder(itemView);
    }
 
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        
        Data data = moviesList.get(position);
        holder.title.setText(data.getTitle());
        holder.genre.setText(data.getGenre());
        holder.year.setText(data.getYear());
        holder.ivIcon.setImageBitmap(data.getIvIcon());
        holder.aSwitch.setChecked(data.gettoggle());

    }
 
    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}