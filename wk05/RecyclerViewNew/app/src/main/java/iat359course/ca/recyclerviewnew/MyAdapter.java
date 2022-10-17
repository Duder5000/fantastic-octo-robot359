package iat359course.ca.recyclerviewnew;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public ArrayList<ListItem> list;
    TextView myTextView;
    ImageView myImageView;
    String data;
    Context context;

    public MyAdapter(ArrayList<ListItem> list, Context context) {
        this.list = list;
        this.context = context;
        Log.d("test", "in MyAdapter: " + list.size());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mylayoutlab5, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        Log.d("test", "in onBindViewHolder = " + position);
        //TextView tv = (TextView) holder.itemView;
        TextView tv = holder.myTextView;
        ImageView iv = holder.myImageView;
        holder.myData = list.get(position).additionalData;
        tv.setText(list.get(position).name);
        iv.setImageResource(list.get(position).imageID);
        Log.d("test", "end of onBindViewHolder");
    }


    @Override
    public int getItemCount() {
        Log.d("test", "in getItemCount: " + list.size());
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView myTextView;
        public ImageView myImageView;
        public String myData;
        Context context;

        public MyViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.textViewList);
            myImageView = (ImageView) itemView.findViewById(R.id.imageViewList);
            itemView.setOnClickListener(this);
            context = itemView.getContext();

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context,
                    "You have clicked ",
                    Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, ShowingActivity.class);
            i.putExtra("dataToShow",myData);
            context.startActivity(i);
        }
    }
}
