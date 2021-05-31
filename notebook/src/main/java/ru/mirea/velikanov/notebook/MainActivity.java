package ru.mirea.velikanov.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    private EditText editTextMessage;
    private EditText editTextPath;
    private SharedPreferences preferences;
    final String LAST_FILE = "last file";

    FileOutputStream outputStream;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextMessage = findViewById(R.id.editTextMessage);
        editTextPath = findViewById(R.id.editTextPath);
        preferences = getPreferences(MODE_PRIVATE);

        String lastFile = preferences.getString(LAST_FILE, "Empty");

        Log.i(TAG, lastFile);

        if(!lastFile.equals("Empty")) {
            Log.i(TAG, lastFile + " exists!!!");
            FileInputStream fin = null;
            try {
                Toast.makeText(this, "Found last file named " + lastFile, Toast.LENGTH_SHORT).show();
                fin = openFileInput(lastFile);
                byte[] bytes = new byte[fin.available()];
                fin.read(bytes);
                String text = new String (bytes);
                editTextMessage.setText(text);
            }
            catch(IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
            finally{
                try {
                    if(fin!=null) fin.close();
                }
                catch(IOException ex){
                    Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void onSaveText(View view) {
        Log.i(TAG, "onSaveText");
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(LAST_FILE, editTextPath.getText().toString());
        editor.apply();

        try {
            outputStream = openFileOutput(editTextPath.getText().toString(),
                    Context.MODE_PRIVATE);
            outputStream.write(editTextMessage.getText().toString().getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();

    }
}