package com.luna.lector;

import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import java.util.ArrayList;

public class ListaDePedidos extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    int cont, suma = 0;
    int PreciosArray [] = new int[30];
    RecyclerView recyclerView;
    private Adapter madapter;
    ArrayList<String> items;
    TextView NameClientesillo;
    Button btnscanner2, btnEnviar;
    String datoname1, num = "";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_pedidos);

        btnscanner2 = findViewById(R.id.btnscanner2);
        NameClientesillo = findViewById(R.id.NameClienta);
        btnEnviar = findViewById(R.id.btnEnviar);
        recyclerView = findViewById(R.id.recyclerView);

        //RECIBE EL NOMBRE DEL CLIENTE
        datoname1 = getIntent().getStringExtra("NameCliente1");
        //Toast.makeText(this, "Escaneo exitoso", Toast.LENGTH_SHORT).show();
        NameClientesillo.setText(datoname1 + " Escanea, Modifica y Envia tus pedidos");

        buildrecyclerview();
        btnenviar();
        btnscanner2();

    }//FINAL DEL ONCREATE

    public String ConsegNum(String cadena){

        char [] cade_div = cadena.toCharArray();

        for (int i = 0 ; i < cade_div.length ; i++){
            if (Character.isDigit(cade_div[i])){
                num+= cade_div[i];
            }
        }
        return num;

    }

    public void buildrecyclerview(){
        items = new ArrayList<>();

        String datosend = getIntent().getStringExtra("datosend");
        if(datosend != null){
            ConsegNum(datosend);
            int prec = Integer.parseInt(num);
            items.add(datosend);
            cont=0;
            PreciosArray[cont] = prec;
            num="";
        }

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        madapter = new Adapter(this, items);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(madapter);

        //DESLIZA ITEM PARA ELIMINAR
        ItemTouchHelper.SimpleCallback simpleCallback =
                new RecyclerItemTouchHelper(0,ItemTouchHelper.LEFT, ListaDePedidos.this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
    }

    public void btnscanner2(){
        btnscanner2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intent = new IntentIntegrator(ListaDePedidos.this);
                intent.setPrompt("ESCANEA EL CÓDIGO QR");
                intent.setCameraId(0);
                intent.setOrientationLocked(false);
                intent.setBeepEnabled(false);
                intent.setCaptureActivity(CaptureActivityPortrait.class);
                intent.setBarcodeImageEnabled(false);
                intent.initiateScan();
            }
        });

    }

    public void btnenviar(){
    btnEnviar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent acto = new Intent(ListaDePedidos.this, UnificarPedidos.class);

            for (int i = 0 ; i < PreciosArray.length ; i++){
                suma += PreciosArray[i];
            }
            String hus = String.valueOf(suma);
            Log.d("SUMADEPEDIDOS: ", hus);

            try {
                switch(cont){
                    case 0:
                        acto.putExtra("ordeness0", items.get(0) );      acto.putExtra("cont", cont);
                        acto.putExtra("suma", suma);                    acto.putExtra("datoName1", datoname1);
                        startActivity(acto);
                        overridePendingTransition(R.anim.zoom_back_in,R.anim.zoom_back_out);
                        //overridePendingTransition(R.anim.left_in,R.anim.left_out);
                        //overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                        break;
                    case 1:
                        acto.putExtra("ordeness0", items.get(0) );      acto.putExtra("ordeness1", items.get(1));
                        acto.putExtra("suma", suma);                    acto.putExtra("PreciosArray", PreciosArray);
                        acto.putExtra("cont", cont);
                        acto.putExtra("datoName1", datoname1);
                        overridePendingTransition(R.anim.left_in,R.anim.left_out);
                        startActivity(acto);
                        break;
                    case 2:
                        acto.putExtra("ordeness0", items.get(0) );      acto.putExtra("ordeness1", items.get(1) );
                        acto.putExtra("ordeness2", items.get(2) );      acto.putExtra("cont", cont);
                        acto.putExtra("datoName1", datoname1);          acto.putExtra("suma", suma);
                        startActivity(acto);
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                        break;
                    case 3:
                        acto.putExtra("ordeness0", items.get(0) );      acto.putExtra("ordeness1", items.get(1) );
                        acto.putExtra("ordeness2", items.get(2) );      acto.putExtra("ordeness3", items.get(3) );
                        acto.putExtra("cont", cont);                    acto.putExtra("datoName1", datoname1);
                        acto.putExtra("suma", suma);
                        overridePendingTransition(R.anim.zoom_back_in,R.anim.zoom_back_out);
                        startActivity(acto);
                        break;
                    case 4:
                        acto.putExtra("ordeness0", items.get(0) );      acto.putExtra("ordeness1", items.get(1) );
                        acto.putExtra("ordeness2", items.get(2) );      acto.putExtra("ordeness3", items.get(3) );
                        acto.putExtra("ordeness4", items.get(4) );      acto.putExtra("cont", cont);
                        overridePendingTransition(R.anim.left_in,R.anim.left_out);
                        acto.putExtra("suma", suma);                    acto.putExtra("datoName1", datoname1);
                        startActivity(acto);
                        break;
                    case 5:
                        acto.putExtra("ordeness0", items.get(0) );      acto.putExtra("ordeness1", items.get(1) );
                        acto.putExtra("ordeness2", items.get(2) );      acto.putExtra("ordeness3", items.get(3) );
                        acto.putExtra("ordeness4", items.get(4) );      acto.putExtra("ordeness5", items.get(5) );
                        acto.putExtra("cont", cont);                    acto.putExtra("datoName1", datoname1);
                        acto.putExtra("suma", suma);                    startActivity(acto);
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                        break;
                    case 6:
                        acto.putExtra("ordeness0", items.get(0) );      acto.putExtra("ordeness1", items.get(1) );
                        acto.putExtra("ordeness2", items.get(2) );      acto.putExtra("ordeness3", items.get(3) );
                        acto.putExtra("ordeness4", items.get(4) );      acto.putExtra("ordeness5", items.get(5) );
                        acto.putExtra("ordeness6", items.get(6) );      acto.putExtra("cont", cont);
                        acto.putExtra("datoName1", datoname1);          acto.putExtra("suma", suma);
                        overridePendingTransition(R.anim.zoom_back_in,R.anim.zoom_back_out);
                        startActivity(acto);
                        break;
                    case 7:
                        acto.putExtra("ordeness0", items.get(0) );      acto.putExtra("ordeness1", items.get(1) );
                        acto.putExtra("ordeness2", items.get(2) );      acto.putExtra("ordeness3", items.get(3) );
                        acto.putExtra("ordeness4", items.get(4) );      acto.putExtra("ordeness5", items.get(5) );
                        acto.putExtra("ordeness6", items.get(6) );      acto.putExtra("ordeness7", items.get(7) );
                        acto.putExtra("cont", cont);                    acto.putExtra("datoName1", datoname1);
                        acto.putExtra("suma", suma);
                        overridePendingTransition(R.anim.left_in,R.anim.left_out);
                        startActivity(acto);
                        break;
                    case 8:
                        acto.putExtra("ordeness0", items.get(0) );      acto.putExtra("ordeness1", items.get(1) );
                        acto.putExtra("ordeness2", items.get(2) );      acto.putExtra("ordeness3", items.get(3) );
                        acto.putExtra("ordeness4", items.get(4) );      acto.putExtra("ordeness5", items.get(5) );
                        acto.putExtra("ordeness6", items.get(6) );      acto.putExtra("ordeness7", items.get(7) );
                        acto.putExtra("ordeness8", items.get(8) );      acto.putExtra("cont", cont);
                        acto.putExtra("datoName1", datoname1);          acto.putExtra("suma", suma);
                        startActivity(acto);
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                        break;
                    case 9:
                        acto.putExtra("ordeness0", items.get(0) );      acto.putExtra("ordeness1", items.get(1) );
                        acto.putExtra("ordeness2", items.get(2) );      acto.putExtra("ordeness3", items.get(3) );
                        acto.putExtra("ordeness4", items.get(4) );      acto.putExtra("ordeness5", items.get(5) );
                        acto.putExtra("ordeness6", items.get(6) );      acto.putExtra("ordeness7", items.get(7) );
                        acto.putExtra("ordeness8", items.get(8) );      acto.putExtra("ordeness9", items.get(9) );
                        acto.putExtra("cont", cont);                    acto.putExtra("datoName1", datoname1);
                        acto.putExtra("suma", suma);                    startActivity(acto);
                        overridePendingTransition(R.anim.zoom_back_in,R.anim.zoom_back_out);
                        break;
                    case 10:
                        acto.putExtra("ordeness0", items.get(0) );      acto.putExtra("ordeness1", items.get(1) );
                        acto.putExtra("ordeness2", items.get(2) );      acto.putExtra("ordeness3", items.get(3) );
                        acto.putExtra("ordeness4", items.get(4) );      acto.putExtra("ordeness5", items.get(5) );
                        acto.putExtra("ordeness6", items.get(6) );      acto.putExtra("ordeness7", items.get(7) );
                        acto.putExtra("ordeness8", items.get(8) );      acto.putExtra("ordeness9", items.get(9) );
                        acto.putExtra("ordeness10", items.get(10) );    acto.putExtra("cont", cont);
                        acto.putExtra("suma", suma);                    acto.putExtra("datoName1", datoname1);
                        startActivity(acto);
                        overridePendingTransition(R.anim.left_in,R.anim.left_out);
                        break;
                        default:
                            if (cont == 11){
                                Toast.makeText(ListaDePedidos.this, "No puedes enviar más pedidos", Toast.LENGTH_SHORT).show();
                            }
                }
            } catch (Exception e){
                Toast.makeText(ListaDePedidos.this, "Error al solicitar vista previa", Toast.LENGTH_SHORT).show();
            }

        }
    });
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null){
            if (result.getContents() != null && cont <= 10){
                ConsegNum(result.getContents());
                items.add(result.getContents());
                cont++;
                int prec = Integer.parseInt(num);
                PreciosArray[cont] = prec;
                num="";
            }else{
                Toast.makeText(ListaDePedidos.this, "No se agregó pedido", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(ListaDePedidos.this, "Error al escanear", Toast.LENGTH_SHORT).show();
            super.onActivityResult(requestCode, resultCode, data);
        }
    }//QRdata

    @Override
    public void onSwipe(RecyclerView.ViewHolder viewHolder, int direction, int pos)  {
        if (viewHolder instanceof Adapter.ViewHolder) {
            madapter.removeItem(viewHolder.getAdapterPosition());
            Toast.makeText(this, "Pedido Eliminado", Toast.LENGTH_SHORT).show();
        }
    }



    /*
    @Override
    public void onSwipe(RecyclerView.ViewHolder viewHolder, int direction, int pos) {
        if (viewHolder instanceof Adapter.ViewHolder) {

            String nombreson;
            final int deletedIntex = viewHolder.getAdapterPosition();

            adapter.removeItem(viewHolder.getAdapterPosition());

            recuperarItemBorrado(viewHolder, nombreson, ----, deletedIntex);

        }
    }
        private void recuperarItemBorrado(RecyclerView.ViewHolder viewHolder, String nombreson, final ----,final int deletedIntex) {

            Snackbar snackbar = Snackbar.make(((Adapter.ViewHolder) viewHolder).DeleteItem, nombreson + "Eliminado", Snackbar.LENGTH_LONG);

            snackbar.setAction("Deshacer", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.restoreItem(, deletedIntex);
                }
            });
            snackbar.setActionTextColor(Color.GREEN);
            snackbar.show();
        }
        */
}//FINAL TODITO
