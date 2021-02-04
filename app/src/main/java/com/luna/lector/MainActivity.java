package com.luna.lector;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.airbnb.lottie.LottieAnimationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;



public class MainActivity extends AppCompatActivity {

    Button btnscanner, btnconnect;
    EditText NameClient;
    private WifiManager wifiManager;
    private boolean WifiCon, DataCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       ConstraintLayout constraintLayout = findViewById(R.id.layout);
       AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
       animationDrawable.setEnterFadeDuration(2000);
       animationDrawable.setExitFadeDuration(4000);
       animationDrawable.start();

        btnscanner = findViewById(R.id.btnscanner);
        btnconnect = findViewById(R.id.btnconnect);
        NameClient = findViewById(R.id.NameClient);

        //error = findViewById(R.id.error);

        ConnectNetwork();
        animationtuto();
        btnscanner();

    }//final oncreate

    private void ConnectNetwork() {
        btnconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                ConnectivityManager ConMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeInfo = ConMan.getActiveNetworkInfo();

                if (activeInfo != null && activeInfo.isConnected()){
                    WifiCon = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
                    DataCon = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;

                    if (WifiCon){ // cel por wifi
                        Toast.makeText(MainActivity.this, "Ya estas conectado.", Toast.LENGTH_SHORT).show();
                    } else  if (DataCon){  //cel por datos

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        LayoutInflater inflater = getLayoutInflater();

                        View viewe = inflater.inflate(R.layout.dialog_personalizado, null);
                        builder.setView(viewe);
                        final AlertDialog dialog = builder.create();
                        dialog.setCancelable(false);
                        dialog.show();

                        TextView Mensa = viewe.findViewById(R.id.text_dialog);
                        Mensa.setText("Estas utilizando datos móviles. Para hacer uso de esta aplicación es necesario estar conectado a la red WiFi de nuestro establecimiento.");

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

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    LayoutInflater inflater = getLayoutInflater();

                    View viewe = inflater.inflate(R.layout.dialog_personalizado, null);
                    builder.setView(viewe);
                    final AlertDialog dialog = builder.create();
                    dialog.setCancelable(false);
                    dialog.show();

                    TextView Mensa = viewe.findViewById(R.id.text_dialog);
                    Mensa.setText("No estas conectado. Para hacer uso de esta aplicación es necesario estar conectado a la red WiFi de nuestro establecimiento.");

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

            }
        });
    }

    public void btnscanner (){
        btnscanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombrecliente = NameClient.getText().toString();
                if(nombrecliente.length() == 0){
                    Toast.makeText(MainActivity.this, "Debes ingresar tu nombre para poder continuar...", Toast.LENGTH_SHORT).show();
                }
                if(nombrecliente.length() != 0) {
                    IntentIntegrator intent = new IntentIntegrator(MainActivity.this);
                    //new IntentIntegrator(MainActivity.this).initiateScan();
                    intent.setPrompt("ESCANEA EL CÓDIGO QR");
                    intent.setCameraId(0);
                    intent.setOrientationLocked(false);
                    intent.setBeepEnabled(false);
                    intent.setCaptureActivity(CaptureActivityPortrait.class);
                    intent.setBarcodeImageEnabled(false);
                    intent.initiateScan();
                }
            }
        });
}

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null)
            if (result.getContents() != null){
                    Intent act = new Intent(this, ListaDePedidos.class);
                    act.putExtra("datosend", result.getContents());
                    act.putExtra("NameCliente1", NameClient.getText().toString());
                    startActivity(act);

                //qrcode.setText("El código es:\n" + result.getContents());
            }else{
                //error.setText("Error al escanear el código");
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void animationtuto(){
        LottieAnimationView TutoAnim = findViewById(R.id.TutoAnim);
        TutoAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tuto = new Intent(MainActivity.this, Tutorial.class);
                overridePendingTransition(R.anim.left_in,R.anim.left_out);
                startActivity(tuto);
            }
        });
    }

}// final all

