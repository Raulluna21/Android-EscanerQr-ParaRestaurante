package com.luna.lector;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.provider.Settings;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class UnificarPedidos extends AppCompatActivity {

    TextView orden, NameClientesillo2;
    Button btn_two_funtions, btnSend, btn_edit;
    Random r;
    String num2 = "", datoName2, MessageSend, NameSend, OrdenEditada;
    int numTotal = 2, suma2 = 0, conta, conta2, Ram, suma;
    private int[] PreciosArray2;
    private WifiManager wifiManager;
    private boolean WifiCon, DataCon, running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unificar_pedidos);

        btn_two_funtions = findViewById(R.id.btn_two_funtions);
        btnSend = findViewById(R.id.send);
        btn_edit = findViewById(R.id.btn_edit);
        orden = findViewById(R.id.orden);
        NameClientesillo2 = findViewById(R.id.NameClientesillo2);

        Bundle datosRecibidos3 = this.getIntent().getExtras();
        datoName2 = datosRecibidos3.getString("datoName1");
        OrdenEditada = datosRecibidos3.getString("OrderEdited");

        NameClientesillo2.setText(datoName2 + " estos son los detalles de tu pedido:");
        PreciosArray2 = datosRecibidos3.getIntArray("PreciosArray");

        running = true;
        if(running)numTotal = 2;

        btnsend();
        receiveData();
        SendToEdit();
        btnscanner2();

    } //final oncreate

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
        super.onBackPressed();

        Toast.makeText(this, "Recordatorio para bloquear el regreso", Toast.LENGTH_SHORT).show();

    }

    private void receiveData() {
        Bundle datosRecibidos4 = this.getIntent().getExtras();
        suma = datosRecibidos4.getInt("suma");
        conta = getIntent().getIntExtra("cont", 0);
        conta2 = conta;
        String ordeness0 = getIntent().getStringExtra("ordeness0");
        String ordeness1 = getIntent().getStringExtra("ordeness1");
        String ordeness2 = getIntent().getStringExtra("ordeness2");
        String ordeness3 = getIntent().getStringExtra("ordeness3");
        String ordeness4 = getIntent().getStringExtra("ordeness4");
        String ordeness5 = getIntent().getStringExtra("ordeness5");
        String ordeness6 = getIntent().getStringExtra("ordeness6");
        String ordeness7 = getIntent().getStringExtra("ordeness7");
        String ordeness8 = getIntent().getStringExtra("ordeness8");
        String ordeness9 = getIntent().getStringExtra("ordeness9");
        String ordeness10 = getIntent().getStringExtra("ordeness10");
        String Precio = "Total: ";
        /*
        String ordeness11 = getIntent().getStringExtra("ordeness11");
        String ordeness12 = getIntent().getStringExtra("ordeness12");
        String ordeness13 = getIntent().getStringExtra("ordeness13");
        String ordeness14 = getIntent().getStringExtra("ordeness14");
        String ordeness15 = getIntent().getStringExtra("ordeness15");
        String ordeness16 = getIntent().getStringExtra("ordeness16");
        String ordeness17 = getIntent().getStringExtra("ordeness17");
        String ordeness18 = getIntent().getStringExtra("ordeness18");
        String ordeness19 = getIntent().getStringExtra("ordeness19");
        String ordeness20 = getIntent().getStringExtra("ordeness20");
*/
        if (OrdenEditada != null) {
            orden.setText(OrdenEditada);
        } else {
            try {
                switch (conta) {
                    case 0:
                        orden.setText(ordeness0);
                        break;
                    case 1:
                        orden.setText(ordeness0 + "\n\n" + ordeness1 + "\n\n" + "\n" + Precio + suma);
                        break;
                    case 2:
                        orden.setText(ordeness0 + "\n\n"  + ordeness1 + "\n\n" + ordeness2 + "\n\n" + "\n" + Precio + suma);
                        break;
                    case 3:
                        orden.setText(ordeness0 + "\n\n" + ordeness1 + "\n\n" + ordeness2
                                + "\n\n" + ordeness3 + "\n\n" + "\n" + Precio + suma);
                        break;
                    case 4:
                        orden.setText(ordeness0 + "\n\n"+ ordeness1 + "\n\n" + ordeness2
                                + "\n\n" + ordeness3 + "\n\n" + ordeness4 + "\n\n" + "\n" + Precio + suma);
                        break;
                    case 5:
                        orden.setText(ordeness0 + "\n\n" + ordeness1 + "\n\n" + ordeness2
                                + "\n\n"  + ordeness3 + "\n\n" + ordeness4 + "\n\n" + ordeness5 + "\n\n" + "\n" + Precio + suma);
                        break;
                    case 6:
                        orden.setText(ordeness0 + "\n\n" + ordeness1 + "\n\n" + ordeness2
                                + "\n\n" + ordeness3 + "\n\n" + ordeness4 + "\n\n" + ordeness5
                                + "\n\n" + ordeness6 + "\n\n" + "\n" + Precio + suma);
                        break;
                    case 7:
                        orden.setText(ordeness0 + "\n\n" + ordeness1 + "\n\n" + ordeness2
                                + "\n\n" + ordeness3 + "\n\n" + ordeness4 + "\n\n" + ordeness5
                                + "\n\n" + ordeness6 + "\n\n" + ordeness7 + "\n\n" + "\n" + Precio + suma);
                        break;
                    case 8:
                        orden.setText(ordeness0 + "\n\n" + ordeness1 + "\n\n" + ordeness2
                                + "\n\n" + ordeness3 + "\n\n" + ordeness4 + "\n\n" + ordeness5
                                + "\n\n" +ordeness6 + "\n\n" + ordeness7 + "\n\n" + ordeness8 + "\n\n" + "\n" + Precio + suma);
                        break;
                    case 9:
                        orden.setText(ordeness0 + "\n\n" + ordeness1 + "\n\n" + ordeness2
                                + "\n\n" + ordeness3 + "\n\n" + ordeness4 + "\n\n" + ordeness5
                                + "\n\n" + ordeness6 + "\n\n" + ordeness7 + "\n\n" + ordeness8
                                + "\n\n" + ordeness9 + "\n\n" + "\n" + Precio + suma);
                        break;
                    case 10:
                        orden.setText(ordeness0 + "\n\n" + ordeness1 + "\n\n" + ordeness2
                                + "\n\n" + ordeness3 + "\n\n" + ordeness4 + "\n\n" + ordeness5
                                + "\n\n" + ordeness6 + "\n\n" + ordeness7 + "\n\n" + ordeness8
                                + "\n\n" + ordeness9 + "\n\n" + ordeness10 + "\n\n" + "\n" + Precio + suma);
                        break;
                }
            } catch (Exception e) {
                Toast.makeText(UnificarPedidos.this, "No se recibieron correctamente los pedidos escaneados", Toast.LENGTH_SHORT).show();
            }
        } //final if and else
    }//final metodo receivedata

    public String ConsegNum2(String cadena){

        char [] cade_div = cadena.toCharArray();

        for (int i = 0 ; i < cade_div.length ; i++){
            if (Character.isDigit(cade_div[i])){
                num2+= cade_div[i];
            }
        }
        return num2;
    }

    public void btnscanner2(){
        btn_two_funtions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intent = new IntentIntegrator(UnificarPedidos.this);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null){
            if (result.getContents() != null){

                ConsegNum2(result.getContents());
                int preci = Integer.parseInt(num2);
                conta2++;
                PreciosArray2[conta2] = preci;
                num2="";
                for (int i = 0 ; i < PreciosArray2.length ; i++){
                    suma2 += PreciosArray2[i];
                }
                String hus = String.valueOf(suma2);
                Log.d("SUMADEPEDIDOS2: ", hus);

                orden.setText(orden.getText() + "\n\n" + result.getContents() + "\nPrecio Total "+numTotal +": " + suma2);
                numTotal++;
                suma2=0;
            }else{
                Toast.makeText(UnificarPedidos.this, "No se agregó pedido", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(UnificarPedidos.this, "Error al escanear", Toast.LENGTH_SHORT).show();
            super.onActivityResult(requestCode, resultCode, data);
        }
    }//QRdata

    private void SendToEdit(){

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edite = new Intent(UnificarPedidos.this, EditarPerdido.class);
                edite.putExtra("NameClient2", datoName2);
                edite.putExtra("OrdenToEdit", orden.getText().toString());
                startActivity(edite);
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });
    }

    public void btnsend()   {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    ConnectivityManager ConMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    NetworkInfo activeInfo = ConMan.getActiveNetworkInfo();

                    if (activeInfo != null && activeInfo.isConnected()){
                        WifiCon = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
                        DataCon = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;

                        if (WifiCon){ // cel por wifi

                            r  = new Random();
                            Ram = r.nextInt(10000)+1;
                            String rannum = String.valueOf(Ram);

                            Intent envi = new Intent(UnificarPedidos.this, Activity_Splash_Loading.class);
                            envi.putExtra("numeroRan", Ram);
                            envi.putExtra("pedidodetail", orden.getText().toString());
                            startActivity(envi);

                            MessageSend = orden.getText().toString() + "\n";
                            NameSend = datoName2 + " " + rannum + "\n";
                            Log.d("V1 pedido: ", MessageSend);
                            Log.d("V1 nombre: ", NameSend);
                            myTask mt = new myTask();
                            mt.execute(new String[]{"192.168.1.71", "7800",  NameSend + MessageSend});

                            /*
                            casa 192.168.1.65
                            casa ethernet 192.168.1.71
                            Casa Villas 192.168.1.67
                            COMPU 1593 : 192.168.137.47
                            Inter Personal uaeh: 10.10.165.168
                            mi cel : 192.168.43.225
                            */
                        } else  if (DataCon){  //cel por datos

                            AlertDialog.Builder builder = new AlertDialog.Builder(UnificarPedidos.this);
                            LayoutInflater inflater = getLayoutInflater();

                            View viewe = inflater.inflate(R.layout.dialog_personalizado, null);
                            builder.setView(viewe);
                            final AlertDialog dialog = builder.create();
                            dialog.setCancelable(false);
                            dialog.show();

                            TextView Mensa = viewe.findViewById(R.id.text_dialog);
                            Mensa.setText("Estas utilizando datos móviles. Para enviar tu pedido es necesario estar conectado a la red WiFi de nuestro establecimiento.");

                            Button Conectar = viewe.findViewById(R.id.Conectar);
                            Conectar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    wifiManager.setWifiEnabled(true);
                                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                                    dialog.dismiss();
                                }
                            });
                            Button btnCancelar = viewe.findViewById(R.id.btnCancelar);
                            btnCancelar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                }
                            });
                        }
                    } else { //ninguno

                        AlertDialog.Builder builder = new AlertDialog.Builder(UnificarPedidos.this);
                        LayoutInflater inflater = getLayoutInflater();

                        View viewe = inflater.inflate(R.layout.dialog_personalizado, null);
                        builder.setView(viewe);
                        final AlertDialog dialog = builder.create();
                        dialog.setCancelable(false);
                        dialog.show();

                        TextView Mensa = viewe.findViewById(R.id.text_dialog);
                        Mensa.setText("No estas conectado. Para enviar tu pedido es necesario estar conectado a la red WiFi de nuestro establecimiento.");

                        Button Conectar = viewe.findViewById(R.id.Conectar);
                        Conectar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                wifiManager.setWifiEnabled(true);
                                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                                dialog.dismiss();
                            }
                        });

                        Button btnCancelar = viewe.findViewById(R.id.btnCancelar);
                        btnCancelar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                    } //final if
                    } catch (Exception e){
                        Toast.makeText(UnificarPedidos.this, "Error al enviar pedido al chef", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    class myTask extends AsyncTask<String, Void, String> {
        //void myTask(){
        Socket s;
        PrintWriter pw;

        @Override
        protected String doInBackground(String... params) {
            MessageSend = null;
            //NameSend = null;
            try {
                s = new Socket(params[0],Integer.parseInt(params[1]));
                InputStream is = s.getInputStream();
                pw = new PrintWriter(s.getOutputStream(),true);
                pw.print(params[2]);
                pw.flush();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                MessageSend = br.readLine();
                s.close();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }catch (UnknownHostException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }




}//final
