package bc.contactos.servicio;

import bc.contactos.modelo.Contacto;

import java.util.List;

public interface IContactoServicio {

    public List<Contacto> listarContactos();
    public Contacto buscarContacto(Long contactoId);
    public void guardarContacto(Contacto contacto);
    public void eliminarContacto(Contacto contacto);
}
