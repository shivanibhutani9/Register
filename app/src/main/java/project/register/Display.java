package project.register;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Display extends AppCompatActivity {

    TextView Data;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Data=(TextView)findViewById(R.id.data);
            Intent intent=getIntent();
            Data.setText(intent.getStringExtra("data"));

    }
}
