package com.luna.lector;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class DetalleItem extends AppCompatActivity {

    TextView Details;
    Button btn_back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalleitem);

        Details = findViewById(R.id.Details);
        btn_back = findViewById(R.id.btn_back);

        Bundle datosRecibidos = this.getIntent().getExtras();
        String detalle = datosRecibidos.getString("DetalleItem");


        Details.setText(detalle);
        //Details.setSelection(Details.length());

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
                overridePendingTransition(R.anim.zoom_back_in,R.anim.zoom_back_out);
                //Toast.makeText(DetalleItem.this, Integer.toString(posi), Toast.LENGTH_LONG).show();
                //overridePendingTransition(R.anim.left_in,R.anim.left_out);
                //overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
    }//FINAL ONCREATE

    @Override
    public void onBackPressed () {
        super.onBackPressed();
            overridePendingTransition(R.anim.zoom_back_in,R.anim.zoom_back_out);
        //overridePendingTransition(R.anim.left_in,R.anim.left_out);
        //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }




} //FINAL

