package antho.demo_jwt.llantas.cat_rines;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RinesService {

    private final RinesRepository rinesRepository;

    public List<Rines> getAllRines() {
        return rinesRepository.findByOpcActivoTrueOrderByIdRinDesc();
    }

    public void registerRines(Rines request) {
        // Revisa si existe
        if (rinesRepository.findByNomRin(request.getNomRin()).isPresent()) {
            throw new IllegalStateException("Ese rin ya existe");
        }

        if (request.getFecAlta() == null) {
            request.setFecAlta(LocalDateTime.now(ZoneId.of("America/Mazatlan")));
        }
        if (request.getOpcActivo() == null) {
            request.setOpcActivo(true);
        }
        // Guardar
        rinesRepository.save(request);
    }

    //editar el rin (solo nombre) y actualizar a la fecha actual
    public void editRines(Rines request) {
        Rines rines = rinesRepository.findById(request.getIdRin()).orElseThrow(() -> new RuntimeException("Rin no encontrado"));
        rines.setNomRin(request.getNomRin());
        // Asignar la fecha actual
        rines.setFecAlta(LocalDateTime.now(ZoneId.of("America/Mazatlan")));
        rinesRepository.save(rines);
    }

    //eliminar rin (solo desactivar)
    public void deleteRines(Integer id) {
        Rines rines = rinesRepository.findById(id).orElseThrow(() -> new RuntimeException("Rin no encontrado"));
        rines.setOpcActivo(false);
        rinesRepository.save(rines);
        //rinesRepository.delete(rines);
    }

    //reactivar rin
    public void reactivateRines(Integer id) {
        Rines rines = rinesRepository.findById(id).orElseThrow(() -> new RuntimeException("Rin no encontrado"));
        rines.setOpcActivo(true);
        rinesRepository.save(rines);
    }

}
