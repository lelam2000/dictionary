package com.example.dic_v1.infor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dic_v1.DatabaseAccess;
import com.example.dic_v1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class infor_ev extends AppCompatActivity {
    TextView textView1,textView2;
    String s1,s2;
    ImageView img;
    TextToSpeech t1;
    FloatingActionButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_ev);
        Intent intent=getIntent();
        textView1=findViewById(R.id.txt_hienthiev1);
        textView2=findViewById(R.id.txt_hienthiev2);
        img=findViewById(R.id.phat_am);
        button=findViewById(R.id.nut_like);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = textView1.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        s1 = intent.getStringExtra("n1");

        try {
            DatabaseAccess databaseAccess= DatabaseAccess.getInstance(this);
            databaseAccess.open();
            String definision=databaseAccess.getDefinition(s1);
            databaseAccess.close();
            textView1.setText(s1);
            textView2.setText(Html.fromHtml(definision));
        }catch (Exception e)
        {
            Log.d("loi2",e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.nutback,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.back:
            {
                finish();
                break;
            }
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}