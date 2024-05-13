package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView Name;
    ImageButton[][] Buttons = new ImageButton[3][3];
    Button Refresh;
    ImageView Turn;
    int turn = 1;
    int[][] BState = new int[3][3];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = (TextView) findViewById(R.id.Name);
        Refresh = (Button) findViewById(R.id.BRefresh);
        Refresh.setOnClickListener(new Refresher());
        Turn = (ImageView) findViewById(R.id.Turn);
        Buttons[0][0] = (ImageButton) findViewById(R.id.But11);
        Buttons[0][1] = (ImageButton) findViewById(R.id.But12);
        Buttons[0][2] = (ImageButton) findViewById(R.id.But13);
        Buttons[1][0] = (ImageButton) findViewById(R.id.But21);
        Buttons[1][1] = (ImageButton) findViewById(R.id.But22);
        Buttons[1][2] = (ImageButton) findViewById(R.id.But23);
        Buttons[2][0] = (ImageButton) findViewById(R.id.But31);
        Buttons[2][1] = (ImageButton) findViewById(R.id.But32);
        Buttons[2][2] = (ImageButton) findViewById(R.id.But33);
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                BState[i][j] = 0;
                Buttons[i][j].setOnClickListener(new Click(i, j));

            }
        }
    }
    public class Refresher implements View.OnClickListener{
        public void onClick(View v) {
            for (int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    BState[i][j] = 0;
                    Buttons[i][j].setImageResource(0);
                }
            }
            Name.setText("Tic-Tac-Toe");
            turn = 1;
            Turn.setImageResource(R.drawable.crest);
        }
    }
    public class Click implements View.OnClickListener {
        int m, n;
        Click(int i, int j){
            m = i;
            n = j;
        }
        public void onClick(View v) {
            if (BState[m][n] == 0){
                if (turn == 1){
                    Buttons[m][n].setImageResource(R.drawable.crest);
                    BState[m][n] = 1;
                    turn = 2;
                    Turn.setImageResource(R.drawable.zero);
                }
                else if (turn == 2){
                    Buttons[m][n].setImageResource(R.drawable.zero);
                    BState[m][n] = 2;
                    turn = 1;
                    Turn.setImageResource(R.drawable.crest);
                }
                for(int i = 0; i < 3; i++) {
                    if (BState[i][0] == 1 && BState[i][1] == 1 && BState[i][2] == 1) {
                       XWin();
                    }
                    if (BState[i][0] == 2 && BState[i][1] == 2 && BState[i][2] == 2) {
                        OWin();
                    }
                }
                for(int i = 0; i < 3; i++) {
                    if (BState[0][i] == 1 && BState[1][i] == 1 && BState[2][i] == 1) {
                        XWin();
                    }
                    if (BState[0][i] == 2 && BState[1][i] == 2 && BState[2][i] == 2) {
                        OWin();
                    }
                }
                if (BState[0][0] == 1 && BState[1][1] == 1 && BState[2][2] == 1) {
                    XWin();
                }
                if (BState[0][0] == 2 && BState[1][1] == 2 && BState[2][2] == 2) {
                    OWin();
                }
                if (BState[2][0] == 1 && BState[1][1] == 1 && BState[0][2] == 1) {
                    XWin();
                }
                if (BState[2][0] == 2 && BState[1][1] == 2 && BState[0][2] == 2) {
                    OWin();
                }
            }

        }
    }
    public void XWin(){
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                BState[i][j] = -1;
            }
        }
        Name.setText("X Win");
    }
    public void OWin(){
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                BState[i][j] = -1;
            }
        }
        Name.setText("O Win");
    }
}
