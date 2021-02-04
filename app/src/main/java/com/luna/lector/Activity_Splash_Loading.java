package com.luna.lector;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class Activity_Splash_Loading extends AppCompatActivity {

    Thread timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_loading);

        Bundle datosRecibidos5 = this.getIntent().getExtras();
        final String PedidoVista = datosRecibidos5.getString("pedidodetail");
        final int numeroRan = datosRecibidos5.getInt("numeroRan");

        timer = new Thread(){
            @Override
            public void run() {
                try{
                    synchronized (this){
                        wait(8000);
                    }
                } catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent splash2 = new Intent(Activity_Splash_Loading.this, Activity_Splash_Loading_dos.class);
                    splash2.putExtra("PedidoVista", PedidoVista);
                    splash2.putExtra("numeroRan2", numeroRan);
                    startActivity(splash2);
                    finish();
                }

            }//RUN
        };  //THREAD
        timer.start();



    }//final oncreate





}//final all
