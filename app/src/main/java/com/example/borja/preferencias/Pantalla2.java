package com.example.borja.preferencias;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Pantalla2 extends AppCompatActivity implements View.OnClickListener{
    private Button botonVolver;
    private EditText txtNombre, txtDni, txtFnac, txtSexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);

        getViews();
        getUserPreferences();
        disableOptions();
        setController();
    }

    private void getViews() {
        txtNombre = (EditText) findViewById(R.id.editTextNom);
        txtDni = (EditText) findViewById(R.id.editTextDni);
        txtFnac = (EditText) findViewById(R.id.editTextFnac);
        txtSexo = (EditText) findViewById(R.id.editTextSexo);
        botonVolver = (Button)findViewById(R.id.buttonVolver);
    }

    private void getUserPreferences() {
        // Recuperamos el objeto de preferencias compartidas
        SharedPreferences mySharedPreferences = getSharedPreferences(Principal.PREFS, Activity.MODE_PRIVATE);

        // Mostramos en cada view el contenido guardado en las preferencias
        txtNombre.setText(mySharedPreferences.getString("nombre",""));
        txtDni.setText(mySharedPreferences.getString("dni",""));
        txtFnac.setText(mySharedPreferences.getString("fechaNacimiento",""));
        txtSexo.setText(mySharedPreferences.getString("sexo",""));
    }

    private void setController() {
        botonVolver.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Comprobamos si el usuario ha hecho click en cancelar (aunque no es necesario si sólo hay un botón)
        if (v.getId() == botonVolver.getId()) {
            finish();
        }
    }

    private void disableOptions() {
        // Deshabilitamos los Views ya que no queremos que se pueda modificar su contenido en esta Activity
        txtNombre.setEnabled(false);
        txtDni.setEnabled(false);
        txtFnac.setEnabled(false);
        txtSexo.setEnabled(false);
    }
}