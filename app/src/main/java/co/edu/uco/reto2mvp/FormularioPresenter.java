package co.edu.uco.reto2mvp;

public class FormularioPresenter implements FormularioMVP.Presenter{

    private FormularioMVP.View view;
    private FormularioMVP.Model model;

    public FormularioPresenter(FormularioMVP.View view) {
        this.view = view;
        model = new FormularioModel(this);
    }

    @Override
    public void validarInformacionNull() {
        if(view != null){
            model.setNombre(view.getNombre());
            model.setApellido(view.getApellido());
            model.setFecha(view.getFecha());
            model.setMessage(view.getMessage());
            model.validarInformacionNull();
        }
    }

    @Override
    public void validarInformacionFormulario() {
        if(view != null){
            model.validarInformacionFormulario();

            System.out.println("entro" + model.validarInformacionFormulario());
        }
    }

    @Override
    public void requerirNombre() {
        if(view != null){
            view.requerirNombre();
        }
    }

    @Override
    public void requerirApellido() {
        if(view != null){
            view.requerirApellido();
        }
    }

    @Override
    public void requerirFecha() {
        if(view != null){
            view.requerirFecha();
        }
    }

}
