package com.example.oracle;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RegisterActivity extends AsyncTask<String, Void, String> {

    String sendMSG, receiveMSg;

    @Override
    protected String doInBackground(String... strings) {
        try{
            String str;


            //접속할 서버 주소(이클립스에서 android.jsp 실행시 웹브라우저 주소
            //92.168.35.191:1521:xepdb1
            URL url= new URL("http://192.168.35.191:1512/DBConn/android/androidDB.jsp");

            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type","application/x-www.form-urlencoded");
            conn.setRequestMethod("POST");
            OutputStreamWriter osw =new OutputStreamWriter(conn.getOutputStream(),"UTF-8");

            //전송할 데이터. Get 방식으로 작성
            sendMSG="id="+strings[0]+"&pw="+strings[1];

            osw.write(sendMSG);
            osw.flush();

            //jsp와 통신 성공 시 수행
            if(conn.getResponseCode()==conn.HTTP_OK){
                InputStreamReader tmp= new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader=new BufferedReader(tmp);
                StringBuffer buffer =new StringBuffer();

                str=reader.readLine();
                //jsp에서 보낸 값을 받는 부분
                while(str!=null){
                    buffer.append(str);
                }
                receiveMSg=buffer.toString();
            }else{
                //통신실패

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }//
        //jsp로부터 받은 리턴값
        return receiveMSg;
    }

}