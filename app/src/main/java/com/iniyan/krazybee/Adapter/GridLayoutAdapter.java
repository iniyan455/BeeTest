package com.iniyan.krazybee.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.iniyan.krazybee.Model.AlbumData;
import com.iniyan.krazybee.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GridLayoutAdapter extends RecyclerView.Adapter<GridLayoutAdapter.viewHolder>{

    private ArrayList<AlbumData> arrayList;
    private FragmentActivity context;

    public GridLayoutAdapter(ArrayList<AlbumData> arrayList, FragmentActivity context) {
        this.arrayList = arrayList;
        this.context=context;
    }

    @NotNull
    @Override
    public  viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, viewGroup, false);
        return new viewHolder(view);
    }
    @Override
    public  void onBindViewHolder(viewHolder viewHolder, int position) {
        viewHolder.name.setText(arrayList.get(position).getTitle());

        Picasso.get()
                .load(arrayList.get(position).getThumbnailUrl())
                .placeholder(android.R.drawable.stat_notify_error)
                .error(R.drawable.ic_launcher_background)
                .into(viewHolder.image);

     }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;

        viewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            image = (ImageView) itemView.findViewById(R.id.image);

        }
    }
}
