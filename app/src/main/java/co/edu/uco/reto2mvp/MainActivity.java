package co.edu.uco.reto2mvp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements FormularioMVP.View {

    private Button btnSelectDate;
    private TextView txtDate;
    private TextView mensaje;
    private EditText inputNombre;
    private EditText inputApellido;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    private Button btnEnviar;
    FormularioMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

        if (presenter == null){
            btnEnviar.setOnClickListener(v-> presenter.validarInformacionNull());

        }else {
            calculateAge();
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    @SuppressLint("WrongViewCast")
    public void initComponents(){
        btnSelectDate = findViewById(R.id.btnSelectDate);
        txtDate = findViewById(R.id.txtDate);
        btnEnviar=findViewById(R.id.btnEnviar);
        inputNombre = findViewById(R.id.inputNombre);
        inputApellido = findViewById(R.id.inputApellido);
        mensaje = findViewById(R.id.mensaje);
        presenter = new FormularioPresenter(this);
    }

    @Override
    public String getNombre() {
        return inputNombre.getText().toString();
    }

    @Override
    public void requerirNombre() {
        inputNombre.setError(getString(R.string.nombre_requerido));
    }

    @Override
    public String getApellido() {
        return inputApellido.getText().toString();
    }

    @Override
    public void requerirApellido() {
        inputApellido.setError(getString(R.string.apellido_requerido));
    }

    @Override
    public String getFecha() {
        return txtDate.getText().toString();
    }

    @Override
    public void requerirFecha() {
        txtDate.setError(getString(R.string.fecha_requerido));
    }

    @Override
    public String getMessage() {
        return mensaje.getText().toString();
    }

    public void selectDate(View view) {
        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int birthDay = calendar.get(Calendar.DAY_OF_MONTH);
                int birthMonth = calendar.get(Calendar.MONTH);
                int birthYear = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        txtDate.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                }, birthYear, birthMonth, birthDay);
                datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });
    }

    public void calculateAge() {

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = inputNombre.getText().toString();
                String apellido = inputApellido.getText().toString();
                String fecha = txtDate.getText().toString();
                String message = mensaje.getText().toString();

                calendar = Calendar.getInstance();
                int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
                int currentMonth = calendar.get(Calendar.MONTH);
                int currentYear = calendar.get(Calendar.YEAR);

                if(txtDate.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), R.string.textoToast, Toast.LENGTH_SHORT).show();
                }else{
                    String birthDate = txtDate.getText().toString();
                    String[] birthDateArray = birthDate.split("-");

                    int age = currentYear - Integer.parseInt(birthDateArray[2]);

                    if(Integer.parseInt(birthDateArray[1]) < currentMonth){
                        age--;
                    }
                    if(Integer.parseInt(birthDateArray[0]) < currentDay){
                        age++;
                    }
                    if(age >= 18){
                        message = "Usted es mayor de edad. Su edad es: " + age + " años";
                    }
                    else{
                        message = "Su edad es : " + age + " años. No puede continuar porque es menor de edad";
                    }

                    if(age >= 18){
                        Intent intent = new Intent(MainActivity.this, Mostrar.class);
                        intent.putExtra("name", name);
                        intent.putExtra("apellido",apellido);
                        intent.putExtra("fecha",fecha);
                        intent.putExtra("mensaje", message);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}