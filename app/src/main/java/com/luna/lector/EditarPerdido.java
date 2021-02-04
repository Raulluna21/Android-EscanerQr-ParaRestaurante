package com.luna.lector;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditarPerdido extends AppCompatActivity {

    TextView NameClientesillo3;
    EditText OrdenEdit;
    Button btnBack;
    String NameClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editarpedido);

        NameClientesillo3 = findViewById(R.id.NameClientesillo3);
        btnBack = findViewById(R.id.BackTo2);
        OrdenEdit = findViewById(R.id.OrdenEdit);
        //DATOS RECIBIDOS DE UNIFICAR PEDIDOS
        Bundle DatosReceive = this.getIntent().getExtras();
        NameClient = DatosReceive.getString("NameClient2");
        String OrdenToEdit2 = DatosReceive.getString("OrdenToEdit");

        NameClientesillo3.setText(NameClient + " aquí puedes editar tu pedido");
        OrdenEdit.setText(OrdenToEdit2);
        //OrdenEdit.setSelection(OrdenEdit.getText().length());


        btnbackto();

    }// fainal oncreate

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
        super.onBackPressed();
        Intent acte = new Intent(EditarPerdido.this, UnificarPedidos.class);
        acte.putExtra("OrderEdited", OrdenEdit.getText().toString());
        acte.putExtra("datoName1", NameClient);
        startActivity(acte);
        Toast.makeText(EditarPerdido.this, "Guardado...", Toast.LENGTH_SHORT).show();

    }

    public void btnbackto() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent acte = new Intent(EditarPerdido.this, UnificarPedidos.class);
                acte.putExtra("OrderEdited", OrdenEdit.getText().toString());
                acte.putExtra("datoName1", NameClient);
                startActivity(acte);
                Toast.makeText(EditarPerdido.this, "Guardado...", Toast.LENGTH_SHORT).show();
                overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);

            }
        });
    }

}//finalall
