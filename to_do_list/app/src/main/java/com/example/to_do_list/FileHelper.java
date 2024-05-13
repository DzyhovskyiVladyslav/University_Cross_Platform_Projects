package com.example.to_do_list;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHelper {
    public static final String FileName = "listinfo.dat";
    public static void WriteData(ArrayList<String> Tasks, Context Cont){
        try {
            FileOutputStream FOS = Cont.openFileOutput(FileName, Context.MODE_PRIVATE);
            ObjectOutputStream OOS = new ObjectOutputStream(FOS);
            OOS.writeObject(Tasks);
            OOS.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<String> ReadData(Context Cont){
        ArrayList<String> TasksList = null;
        try {
            FileInputStream FIS = Cont.openFileInput(FileName);
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            TasksList = (ArrayList<String>) OIS.readObject();
        } catch (FileNotFoundException e) {
            TasksList = new ArrayList<>();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return TasksList;
    }
}
