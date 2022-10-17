package iat359course.ca.recyclerviewnew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ShowingActivity extends AppCompatActivity {

    TextView showData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing);

        showData = (TextView) findViewById(R.id.showData);
        Bundle b = getIntent().getExtras();
        showData.setText(b.getString("dataToShow"));
    }
}