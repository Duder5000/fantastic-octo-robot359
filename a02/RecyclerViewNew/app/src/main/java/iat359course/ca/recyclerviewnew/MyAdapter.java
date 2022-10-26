package iat359course.ca.recyclerviewnew;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public ArrayList<String> sensorList;
    Context context;
    List<Sensor> deviceSensorsAdp;

    public MyAdapter(ArrayList<String> list, Context context, List<Sensor> deviceSensorsAdp) {
        sensorList = list;
        this.context = context;
        this.deviceSensorsAdp = deviceSensorsAdp;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mylayoutlab5, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        TextView tv = holder.myTextView;
        holder.myData = sensorList.get(position);
        tv.setText(sensorList.get(position));
    }


    @Override
    public int getItemCount() {
        return sensorList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView myTextView;
        public String myData;
        Context context;

        Sensor clickedSensor;

        public MyViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.textViewList);
            itemView.setOnClickListener(this);
            context = itemView.getContext();
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(context, ShowingActivity.class);
            i.putExtra("dataToShow", myData);
//            i.putExtra("sensorData", clickedSensor);
            context.startActivity(i);
        }
    }
}
