package com.example.borja.preferencias;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.HashMap;
import java.util.Map;

public class Principal extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener{
    public static final String PREFS = "My preferences";
    private Button botonEnviar;
    private EditText txtNombre, txtDni, txtFnac;
    private RadioGroup radioGroupSexo;
    private String sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        getViews();
        setController();
    }

    private void getViews() {
        txtNombre = (EditText) findViewById(R.id.editTextNom);
        txtDni = (EditText) findViewById(R.id.editTextDni);
        txtFnac = (EditText) findViewById(R.id.editTextFnac);
        radioGroupSexo = (RadioGroup) findViewById(R.id.radioGroup);
        botonEnviar = (Button)findViewById(R.id.buttonEnviar);
    }

    private void setController() {
        botonEnviar.setOnClickListener(this);
        radioGroupSexo.setOnCheckedChangeListener(this);
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

        userPreferences.put("nombre",txtNombre.getText().toString());
        userPreferences.put("dni",txtDni.getText().toString());
        userPreferences.put("fechaNacimiento",txtFnac.getText().toString());
        userPreferences.put("sexo",this.sexo);

        return userPreferences;
    }

    private void savePreferences(SharedPreferences.Editor editor, HashMap<String, String> prefs) {
        for (Map.Entry<String, String> p : prefs.entrySet()) {
            editor.putString(p.getKey(),p.getValue());
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        // Obtenemos el id del radio button seleccionado
        int selectedId = group.getCheckedRadioButtonId();
        // Encontramos el radio a partir del id anterior
        RadioButton radio = (RadioButton) findViewById(selectedId);
        // Seteamos el valor de la variable que pasaremos a Pantalla2
        sexo = (String) radio.getText();
    }
}