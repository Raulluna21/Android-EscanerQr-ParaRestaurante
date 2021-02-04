package com.luna.lector;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_Splash_Loading_dos extends AppCompatActivity {

    TextView PedidoCOde, PedidoReceived;
    Button btn_Finish;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_loading_dos);

        PedidoCOde = findViewById(R.id.PedidoCOde);
        PedidoReceived = findViewById(R.id.PedidoRecibido);
        btn_Finish = findViewById(R.id.btn_finish);

        Bundle datosRecibidos5 = this.getIntent().getExtras();
        final String PedidoVista2 = datosRecibidos5.getString("PedidoVista");
        final int numeroRan2 = datosRecibidos5.getInt("numeroRan2");
        PedidoReceived.setText(PedidoVista2);


        PedidoCOde.setText("Código de pedido #" + numeroRan2);


        btn_Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent splash2 = new Intent(Activity_Splash_Loading_dos.this, MainActivity.class);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                startActivity(splash2);
                finish();
            }
        });


    }//final oncreate



}//final all
