package co.edu.uco.reto2mvp;

import static android.widget.Toast.makeText;


import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

public class FormularioModel implements FormularioMVP.Model{

    private final FormularioMVP.Presenter presenter;
    private String nombre;
    private String apellido;
    private String fecha;
    private String mensaje;

    public FormularioModel(FormularioMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public void setMessage(String mensaje) {
        this.mensaje = mensaje;
    }

    public void validarInformacionNull() {
        if(nombre == null || "".equals(nombre)){
            presenter.requerirNombre();
            presenter.requerirApellido();
            presenter.requerirFecha();
        }
    }

    public int validarInformacionFormulario() {

        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);

        String birthDate = fecha;
        String[] birthDateArray = birthDate.split("-");

        int age = currentYear - Integer.parseInt(birthDateArray[2]);


        if(Integer.parseInt(birthDateArray[1]) < currentMonth){
            age--;
        }
        if(Integer.parseInt(birthDateArray[0]) < currentDay){
            age++;
        }

        return age;

    }



}
