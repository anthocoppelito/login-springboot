package antho.demo_jwt.llantas.cat_modelos;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ModeloService {

    private final ModeloRepository modeloRepository;

    public List<Modelo> getAllModelos() {
        return modeloRepository.findByOpcActivoTrueOrderByIdModeloDesc();
    }

    public void registerModelo(Modelo request) {
        // Revisa si existe
        if (modeloRepository.findByNomModelos(request.getNomModelos()).isPresent()) {
            throw new IllegalStateException("Ese modelo ya existe");
        }
        if (request.getFecAlta() == null) {
            request.setFecAlta(LocalDateTime.now(ZoneId.of("America/Mazatlan")));
        }
        if (request.getOpcActivo() == null) {
            request.setOpcActivo(true);
        }



        // Guardar
        modeloRepository.save(request);
    }

    //editar modelo (solo nombre) y actualizar a la fecha actual
    public void editModelo(Modelo request) {
        Modelo modelo = modeloRepository.findById(request.getIdModelo()).orElseThrow(() -> new RuntimeException("Modelo no encontrado"));
        modelo.setNomModelos(request.getNomModelos());
        // Asignar la fecha actual
        modelo.setFecAlta(LocalDateTime.now(ZoneId.of("America/Mazatlan")));
        modeloRepository.save(modelo);
    }

    //eliminar modelo (solo desactivar)
    public void deleteModelo(Integer id) {
        Modelo modelo = modeloRepository.findById(id).orElseThrow(() -> new RuntimeException("Modelo no encontrado"));
        modelo.setOpcActivo(false);
        modeloRepository.save(modelo);
        //modeloRepository.delete(modelo);
    }

    //reactivar modelo
    public void reactivateModelo(Integer id) {
        Modelo modelo = modeloRepository.findById(id).orElseThrow(() -> new RuntimeException("Modelo no encontrado"));
        modelo.setOpcActivo(true);
        modeloRepository.save(modelo);
    }

}
