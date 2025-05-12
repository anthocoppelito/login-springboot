package antho.demo_jwt.llantas.cat_modelos;

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
        // Guardar
        modeloRepository.save(request);
    }

}
