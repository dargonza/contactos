package bc.contactos.controlador;

import bc.contactos.modelo.Contacto;
import bc.contactos.servicio.ContactoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContactoControlador {
    private static final Logger logger = LoggerFactory.getLogger(ContactoControlador.class);

    @Autowired
    ContactoServicio contactoServicio;

    @GetMapping("/")
    public String iniciar(ModelMap model) {
        List<Contacto> contactos = contactoServicio.listarContactos();
        contactos.forEach((contacto) -> logger.info(contacto.toString())); // Opcional
        model.put("contactos", contactos);
        return "index";
    }

    @GetMapping("/agregar")
    public String mostrarAgregar() {
        return "agregar";
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute("contactoForm") Contacto contactoNuevo) {
        logger.info("Contacto a agregar: " + contactoNuevo);  // Opcional
        contactoServicio.guardarContacto(contactoNuevo);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable(value = "id") Long contactoId, ModelMap model) {
        Contacto contacto = contactoServicio.buscarContacto(contactoId);
        logger.info("Contacto a editar (mostrar): " + contacto);  // Opcional
        model.put("contacto", contacto);
        return "editar";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute("contacto") Contacto contactoEditado) {
        logger.info("Contacto a guardar (editar): " + contactoEditado);  // Opcional
        contactoServicio.guardarContacto(contactoEditado);
        return "redirect:/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long contactoId) {
        Contacto contacto = new Contacto();
        contacto.setId(contactoId);
        logger.info("Contacto a eliminar: " + contacto);  // Opcional
        contactoServicio.eliminarContacto(contacto);
        return "redirect:/";
    }
}
