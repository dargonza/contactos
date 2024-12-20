package bc.contactos.repositorio;

import bc.contactos.modelo.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepositorio  extends JpaRepository<Contacto, Long> {
}
