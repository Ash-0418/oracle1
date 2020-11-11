package com.example.oracle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button registerBtn;
    EditText idet,pwet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setTitle("ORACLE");

        registerBtn =(Button)findViewById(R.id.register_btn);
        idet=(EditText)findViewById(R.id.register_id);
        pwet=(EditText)findViewById(R.id.register_pw);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Toast.makeText(MainActivity.this,"버튼눌림",Toast.LENGTH_SHORT).show();
                    String result;
                    String id=idet.getText().toString();
                    String pw=pwet.getText().toString();

                    RegisterActivity task=new RegisterActivity();
                    result=task.execute(id,pw).get();


                }catch (Exception e){
                    Log.i("DBtest",".......ERROR...........");
                }
            }
        });
    }
}