package antho.demo_jwt.llantas.cat_modelos;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ModeloService {

    private final ModeloRepository modeloRepository;

    public List<Modelo> getAllModelos() {
        return modeloRepository.findAll();
    }

    public void registerModelo(Modelo request) {
        // Revisa si existe
        if (modeloRepository.findByNomModelos(request.getNomModelos()).isPresent()) {
            throw new IllegalStateException("Ese modelo ya existe");
        }
        if (request.getFec_alta() == null) {
            request.setFec_alta(LocalDateTime.now(ZoneId.of("America/Mazatlan")));
        }
        if (request.getOpc_activo() == null) {
            request.setOpc_activo(true);
        }



        // Guardar
        modeloRepository.save(request);
    }

}
