package antho.demo_jwt.llantas.cat_marcas;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.data.domain.Sort;
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
        if (marca.getFecAlta() == null) {
            // Asignar la fecha actual si no se proporciona
            marca.setFecAlta(LocalDateTime.now(ZoneId.of("America/Mazatlan")));
            //marca.setFec_alta(new java.util.Date());
        }
        if (marca.getOpcActivo() == null) {
            // Asignar un valor por defecto si no se proporciona
            marca.setOpcActivo(true);
        }


        //Guardar
        marcaRepository.save(marca);
    }

    public List<Marca> getAllMarcas() {
        // Obtener todas las marcas de modo ascendente por ID menos los que no estan activos
        return marcaRepository.findByOpcActivoTrueOrderByIdMarcaDesc();
    }

    //editar marca (solo nombre) y actualizar a la fecha actual
    public void editMarca(Marca request) {
        Marca marca = marcaRepository.findById(request.getIdMarca()).orElseThrow(() -> new RuntimeException("Marca no encontrada"));
        marca.setNomMarcas(request.getNomMarcas());
        // Asignar la fecha actual
        marca.setFecAlta(LocalDateTime.now(ZoneId.of("America/Mazatlan")));
        marcaRepository.save(marca);
    }

    //eliminar marca (solo desactivar)
    public void deleteMarca(Integer id) {
        Marca marca = marcaRepository.findById(id).orElseThrow(() -> new RuntimeException("Marca no encontrada"));
        marca.setOpcActivo(false);
        marcaRepository.save(marca);
        //marcaRepository.delete(marca);
    }

    //eliminar marca (borrado fisico)
    public void deleteMarcaPhysically(Integer id) {
        Marca marca = marcaRepository.findById(id).orElseThrow(() -> new RuntimeException("Marca no encontrada"));
        marcaRepository.delete(marca);
    }

    //reactivar marca
    public void reactivateMarca(Integer id) {
        Marca marca = marcaRepository.findById(id).orElseThrow(() -> new RuntimeException("Marca no encontrada"));
        marca.setOpcActivo(true);
        marcaRepository.save(marca);
    }


}
