package antho.demo_jwt.llantas.cat_marcas;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public void registerMarca(Marca marca) {
        // Revisa si existe
        if (marcaRepository.findByNomMarcas(marca.getNomMarcas()).isPresent()) {
            throw new IllegalStateException("Esa marca ya existe");
        }
        if (marca.getNomMarcas() == null || marca.getNomMarcas().isEmpty()) {
            throw new IllegalStateException("El nombre de la marca no puede estar vacío");
        }
        if (marca.getFec_alta() == null) {
            // Asignar la fecha actual si no se proporciona
            marca.setFec_alta(LocalDateTime.now(ZoneId.of("America/Mazatlan")));
            //marca.setFec_alta(new java.util.Date());
        }
        if (marca.getOpc_activo() == null) {
            // Asignar un valor por defecto si no se proporciona
            marca.setOpc_activo(true);
        }


        //Guardar
        marcaRepository.save(marca);
    }

    public List<Marca> getAllMarcas() {
        // Obtener todas las marcas
        return marcaRepository.findAll();
    }

}
