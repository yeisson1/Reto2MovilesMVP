package co.edu.uco.reto2mvp;

public interface FormularioMVP {

    interface View {

        String getNombre();

        void requerirNombre();

        String getApellido();

        void requerirApellido();

        String getFecha();

        void requerirFecha();

        String getMessage();
    }

    interface Presenter {
        void validarInformacionNull();
        void validarInformacionFormulario();

        void requerirNombre();
        void requerirApellido();
        void requerirFecha();
    }

    interface Model {

        void setNombre(String nombre);
        void setApellido(String apellido);
        void setFecha(String apellido);
        void setMessage(String message);
        void validarInformacionNull();
        int validarInformacionFormulario();
    }
}
