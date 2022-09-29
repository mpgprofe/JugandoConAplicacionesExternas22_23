package com.example.jugandoconaplicacionesexternas22_23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button buttonLanzarWeb, buttonLanzarMapa, buttonWhatsapp, buttonEmail;
EditText editTextWeb, editTextDireccion, editTextAsunto, editTextCuerpo;
String coordenadasInstituto = "38.2999163,-5.2721756";
String coordenadasTorreEifel = "48.8589466,2.2769949";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonLanzarWeb = findViewById(R.id.buttonLanzarWeb);
        editTextWeb = findViewById(R.id.editTextWeb);
        buttonLanzarMapa = findViewById(R.id.button2);
        buttonWhatsapp = findViewById(R.id.buttonLanzarWhatsapp);
        editTextDireccion = findViewById(R.id.editTextTextEmailAddress);
        editTextAsunto = findViewById(R.id.editTextTextAsunto);
        editTextCuerpo = findViewById(R.id.editTextTextMultiLine);
        buttonEmail = findViewById(R.id.buttonCorreo);

        Toast.makeText(this, getString(R.string.saludo), Toast.LENGTH_SHORT).show();


        buttonLanzarWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(editTextWeb.getText().toString()));

                Intent seleccionar = Intent.createChooser(intent, getString(R.string.nota_informativa));


                startActivity(seleccionar);
            }
        });

        buttonLanzarMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:"+coordenadasInstituto));
                startActivity(intent);
            }
        });

        buttonWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Buenos días!");
                intent.setPackage("com.whatsapp");
                try {
                    startActivity(intent);
                }catch (ActivityNotFoundException e){
                    Toast.makeText(MainActivity.this, R.string.debes_instalar_whatsapp, Toast.LENGTH_LONG).show();
                }

            }
        });

        buttonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{editTextDireccion.getText().toString()});
                intent.putExtra(Intent.EXTRA_SUBJECT, editTextAsunto.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, editTextCuerpo.getText().toString());
                intent.setType("message/rfc822");
                startActivity(intent);

            }
        });

    }
}