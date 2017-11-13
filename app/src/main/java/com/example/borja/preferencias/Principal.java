package com.example.borja.preferencias;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class Principal extends AppCompatActivity implements View.OnClickListener{
    public static final String PREFS = "My preferences";
    private Button botonEnviar;
    private EditText txtDia, txtMes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        getViews();
        setController();
    }

    private void getViews() {
        txtDia = (EditText) findViewById(R.id.editTextDia);
        txtMes = (EditText) findViewById(R.id.editTextMes);
        botonEnviar = (Button)findViewById(R.id.buttonEnviar);
    }

    private void setController() {
        botonEnviar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Creamos o recuperamos el objeto de preferencias compartidas
        SharedPreferences mySharedPreferences = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);

        // Obtenemos un editor para modificar las preferencias
        SharedPreferences.Editor editor = mySharedPreferences.edit();

        // Obtenemos los valores introducidos por el usuario
        HashMap<String, String> userPreferences = getUserPreferences();

        // Guardamos los valores en preferencias
        savePreferences(editor, userPreferences);

        // Guardamos los cambios
        editor.commit();

        // Iniciamos la siguiente activity
        startActivity(new Intent(this, Pantalla2.class));
    }

    private HashMap<String, String> getUserPreferences() {
        HashMap<String,String> userPreferences = new HashMap<String,String>();

        userPreferences.put("dia", txtDia.getText().toString());
        userPreferences.put("mes", txtMes.getText().toString());

        return userPreferences;
    }

    private void savePreferences(SharedPreferences.Editor editor, HashMap<String, String> prefs) {
        for (Map.Entry<String, String> p : prefs.entrySet()) {
            editor.putString(p.getKey(),p.getValue());
        }
    }
}