package iat359course.ca.recyclerviewnew;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends Activity {
    RecyclerView myRecycler;
    RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<String> courses = new ArrayList<String>(
            Arrays.asList("IAT381", "IAT351","IAT336", "IAT337", "IAT338", "IAT201", "IAT401", "IAT111", "IAT222", "IAT333", "IAT444", "IAT555"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myRecycler = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        myRecycler.setLayoutManager(mLayoutManager);

        adapter = new MyAdapter(courses, getApplicationContext());
        myRecycler.setAdapter(adapter);
    }
}
