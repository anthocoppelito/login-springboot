package antho.demo_jwt.llantas.cat_rines;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RinesService {

    private final RinesRepository rinesRepository;

    public List<Rines> getAllRines() {
        return rinesRepository.findAll();
    }

    public void registerRines(Rines request) {
        // Revisa si existe
        if (rinesRepository.findByNomRin(request.getNomRin()).isPresent()) {
            throw new IllegalStateException("Ese rin ya existe");
        }
        // Guardar
        rinesRepository.save(request);
    }

}
