package antho.demo_jwt.llantas.ctl_inventariollantas;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Ctl_InventarioLlantaService {

    private final Ctl_InventarioLlantaRepository ctlInventarioLlantaRepository;

    public List<Ctl_InventarioLlanta> getAllInventariosLlanta() {
        return ctlInventarioLlantaRepository.findAll();
    }

    public void registerInventarioLlanta(Ctl_InventarioLlanta request) {
        // Revisa si existe
        //NO SE CHECA AQUI

        // Guardar
        ctlInventarioLlantaRepository.save(request);
    }

}
