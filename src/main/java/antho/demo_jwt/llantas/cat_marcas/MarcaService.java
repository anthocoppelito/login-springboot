package antho.demo_jwt.llantas.cat_marcas;

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
        //Guardar
        marcaRepository.save(marca);
    }

    public List<Marca> getAllMarcas() {
        // Obtener todas las marcas
        return marcaRepository.findAll();
    }

}
