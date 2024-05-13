package com.example.to_do_list;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private EditText Input;
    private Button Add, Clear;
    private ListView Tasks;
    private ArrayList<String> TaskNames;
    private ArrayAdapter<String> Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Input = findViewById(R.id.InputField);
        Add = findViewById(R.id.AddButton);
        Clear = findViewById(R.id.ClearButton);
        Tasks = findViewById(R.id.TasksList);
        TaskNames = FileHelper.ReadData(this);
        Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, TaskNames);
        Tasks.setAdapter(Adapter);
        Add.setOnClickListener(this);
        Clear.setOnClickListener(this);
        Tasks.setOnItemClickListener(this);
    }
    @Override
    public void onClick(View V) {
        switch(V.getId()){
            case R.id.AddButton:
                String TaskEntered = Input.getText().toString();
                if(CheckText(TaskEntered)){
                    Toast.makeText(this, "Поле пусте", Toast.LENGTH_SHORT).show();
                    return;
                }
                Adapter.add(TaskEntered);
                Input.setText("");
                FileHelper.WriteData(TaskNames, this);
                Toast.makeText(this, "Завдання додано", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ClearButton:
                if(TaskNames.size() == 0){
                    Toast.makeText(this, "Список вже очищено", Toast.LENGTH_SHORT).show();
                    return;
                }
                TaskNames.clear();
                Adapter.notifyDataSetChanged();
                FileHelper.WriteData(TaskNames, this);
                Toast.makeText(this, "Список очищено", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    public void onItemClick(AdapterView<?> Parent, View V, int Position, long id) {
        TaskNames.remove(Position);
        Adapter.notifyDataSetChanged();
        FileHelper.WriteData(TaskNames, this);
        Toast.makeText(this, "Завдання прибрано", Toast.LENGTH_SHORT).show();
    }
    public boolean CheckText(String Text){
        if(Text.matches(""))
            return true;
        int count = 0;
        for(int i = 0; i < Text.length(); i++){
            char Symb = Text.charAt(i);
            if(Symb == ' ' || Symb == '\t' || Symb == '\n')
                count++;
        }
        if(count == Text.length())
            return true;
        return false;
    }
}
