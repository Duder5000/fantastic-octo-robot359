package iat359course.ca.recyclerviewnew;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends Activity {
    RecyclerView myRecycler;
    RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<String> courses = new ArrayList<String>(
            Arrays.asList("IAT381", "IAT351","IAT336", "IAT337", "IAT338", "IAT201", "IAT401", "IAT111", "IAT222", "IAT333", "IAT444", "IAT555")
    );
    ArrayList<String> coursesData = new ArrayList<String>(
            Arrays.asList("IAT381", "IAT351","IAT336", "IAT337", "IAT338", "IAT201", "IAT401", "IAT111", "IAT222", "IAT333", "IAT444", "IAT555")
    );
    ArrayList<Integer> images;
    ArrayList<ListItem> masterArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        images = new ArrayList<Integer>(
                Arrays.asList(R.drawable.red, R.drawable.blue,R.drawable.blue, R.drawable.blue, R.drawable.blue, R.drawable.blue, R.drawable.blue, R.drawable.blue, R.drawable.blue, R.drawable.blue, R.drawable.blue, R.drawable.red)
        );

        masterArr = new ArrayList<ListItem>();
        for(int i = 0; i < courses.size(); i++){
            Log.d("test", "adding an item: " + i);
            masterArr.add(new ListItem(courses.get(i), images.get(i), coursesData.get(i)));
        }


        myRecycler = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        myRecycler.setLayoutManager(mLayoutManager);

        adapter = new MyAdapter(masterArr, getApplicationContext());
        myRecycler.setAdapter(adapter);
    }
}
