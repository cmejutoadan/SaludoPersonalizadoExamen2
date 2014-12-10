package com.example.cmejuto.saludopersonalizado;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.cmejuto.saludopersonalizado.R.id.rdbHola;

//ejercicio 1
public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //creamos los objetos
        final TimePicker timePicker;
        final DatePicker datePicker;

        //llamada al constructor
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //instanciamos los objetos
        timePicker = (TimePicker) findViewById(R.id.idtimepicker);
        datePicker = (DatePicker) findViewById(R.id.iddatepicker);
        Button button = (Button) findViewById(R.id.hello);


        //definimos el listener del botón
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //evento: código que ejecutamos al hacer una acción como pinchar en un botón
                EditText text = (EditText) findViewById(R.id.entry);
                String salutation, saludoTipo = null;
                String tipoSalutation = null; //tipo de saludo
                String enteredName = text.getText().toString();

                //aquí referenciamos al radiobutton
                RadioGroup radio = (RadioGroup) findViewById(R.id.RadioGroup01);

                if (R.id.rdbSra == (radio.getCheckedRadioButtonId())) {
                    //para sra
                    salutation = getResources().getString(R.string.saludoSra);
                } else {
                    salutation = getResources().getString(R.string.saludoSr);
                }
                //tostada
                if (text.getText().toString().isEmpty()) {
                    //en una sola pieza. no necesario llamar al método showToast. el metodo showtoast puede ser llamado desde cualquier parte
                    //  Toast.makeText(getApplicationContext(), (R.string.nonombre), Toast.LENGTH_SHORT).show();//no es necesario instanciar el objeto tostada
                    String msg = getString(R.string.nonombre);//puedo definir el string msg aquí o en el método. si lo defnimos aquí podemos sobrecargar el método
                    showToast(msg);// llamamos al método show toast
                    return; //y lo retornamos
                    // el método lo desarrollamos fuera del oncreate, junsto al teminarlo

                }


                //referenciamos el radiobutton2
                RadioGroup radio2 = (RadioGroup) findViewById(R.id.RadioGroup02);

                if (R.id.rdbHola == (radio.getCheckedRadioButtonId())) {
                    //para hola
                    saludoTipo = getResources().getString(R.string.saludoHola);
                } else {
                    saludoTipo = getResources().getString(R.string.saludoAdios);
                }


                salutation = saludoTipo + " " + salutation + " " + enteredName;
                /*TextView out = (TextView) findViewById(R.id.out);
                out.setText(salutation); // comentamos para añadir llamada a nuevo layout*/

                //para mandar a la clase salutation
                Intent intent = new Intent(MyActivity.this, Salutation.class);
                intent.putExtra("salutation", salutation); //salutation es el identificador
                startActivity(intent);
            }
        });
        final CheckBox checkBox = (CheckBox) findViewById(R.id.idcheckbox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkBox.isChecked()) {
                    timePicker.setVisibility(TimePicker.VISIBLE);//timepicker.visible es un Integer
                } else {
                    timePicker.setVisibility(TimePicker.GONE);
                }
            }
        });
        final CheckBox cbxdate = (CheckBox) findViewById(R.id.idcbxdate);
        cbxdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cbxdate.isChecked()) {
                    datePicker.setVisibility(DatePicker.VISIBLE);//datepicker.visible devuelve un integer que indica la visibilidad del objeto
                } else {
                    datePicker.setVisibility(DatePicker.GONE);
                }
            }
        });
    }

    protected void showToast(String msg) {
        Context contexto = getApplicationContext();
        int duracion = Toast.LENGTH_SHORT;
        //String msg = getString(R.string.nonombre);
        Toast tostada = Toast.makeText(contexto, msg, duracion);
        tostada.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
