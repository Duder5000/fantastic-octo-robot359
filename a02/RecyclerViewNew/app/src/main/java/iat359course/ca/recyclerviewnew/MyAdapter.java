package iat359course.ca.recyclerviewnew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public ArrayList<String> list;
    Context context;

    public MyAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        context = context;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        //TextView tv = (TextView) holder.itemView;
        TextView tv = holder.myTextView;
        tv.setText(list.get(position).toString());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView myTextView;

        Context context;

        public MyViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView;
            itemView.setOnClickListener(this);
            context = itemView.getContext();

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context,
                    "You have clicked " + ((TextView) view).getText().toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
