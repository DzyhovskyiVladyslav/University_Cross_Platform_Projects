package com.example.lab_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView OutText;
    Button But;
    EditText InText;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OutText = (TextView) findViewById(R.id.Output);
        But = (Button) findViewById(R.id.ButtonOut);
        InText = (EditText) findViewById(R.id.Input);
        But.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                OutText.setText(InText.getText());
            }
        });
    }
}
