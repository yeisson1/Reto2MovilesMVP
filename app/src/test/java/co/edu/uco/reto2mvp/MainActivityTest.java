package co.edu.uco.reto2mvp;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class MainActivityTest {

    @Mock
    FormularioMVP.View view;
    FormularioMVP.Presenter presenter;
    FormularioMVP.Model model;

    @Before
    public void setUp(){
        presenter = new FormularioPresenter(view);
        model = new FormularioModel(presenter);
    }

    @Test
    public void requerirNombreNull(){
        when(view.getNombre()).thenReturn(null);
        model.setNombre(view.getNombre());
        model.validarInformacionNull();
        verify(view).requerirNombre();
    }

    @Test
    public void requerirNombreVacio(){
        when(view.getNombre()).thenReturn("");
        model.setNombre(view.getNombre());
        model.validarInformacionNull();
        verify(view).requerirNombre();
    }

    @Test
    public void requerirApellidoNull(){
        when(view.getApellido()).thenReturn(null);
        model.setApellido(view.getApellido());
        model.validarInformacionNull();
        verify(view).requerirApellido();
    }

    @Test
    public void requerirApellidoVacio(){
        when(view.getApellido()).thenReturn("");
        model.setApellido(view.getApellido());
        model.validarInformacionNull();
        verify(view).requerirApellido();
    }

    @Test
    public void requerirFechaNull(){
        when(view.getFecha()).thenReturn(null);
        model.setFecha(view.getFecha());
        model.validarInformacionNull();
        verify(view).requerirFecha();
    }

    @Test
    public void requerirFechaVacio(){
        when(view.getFecha()).thenReturn("");
        model.setFecha(view.getFecha());
        model.validarInformacionNull();
        verify(view).requerirFecha();
    }
}