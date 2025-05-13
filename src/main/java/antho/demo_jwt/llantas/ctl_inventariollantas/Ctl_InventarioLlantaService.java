package antho.demo_jwt.llantas.ctl_inventariollantas;

import java.util.List;

import org.springframework.stereotype.Service;

import antho.demo_jwt.llantas.cat_marcas.Marca;
import antho.demo_jwt.llantas.cat_marcas.MarcaRepository;
import antho.demo_jwt.llantas.cat_modelos.Modelo;
import antho.demo_jwt.llantas.cat_modelos.ModeloRepository;
import antho.demo_jwt.llantas.cat_rines.Rines;
import antho.demo_jwt.llantas.cat_rines.RinesRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Ctl_InventarioLlantaService {

    private final Ctl_InventarioLlantaRepository ctlInventarioLlantaRepository;
    private final MarcaRepository marcaRepository;
    private final ModeloRepository modeloRepository;
    private final RinesRepository rinesRepository;

    public List<Ctl_InventarioLlanta> getAllInventariosLlanta() {
        return ctlInventarioLlantaRepository.findAll();
    }

    public void registerInventarioLlanta(Ctl_InventarioLlantaDTO request) {
        
        //obtener la marca y revisar si existen
        Marca marca = marcaRepository.findById(request.id_marca)
            .orElseThrow(() -> new RuntimeException("Marca no encontrada, verifica el ID"));
        Modelo modelo = modeloRepository.findById(request.id_modelo)
            .orElseThrow(() -> new RuntimeException("Modelo no encontrado, verifica el ID"));
        Rines rines = rinesRepository.findById(request.id_rin)
            .orElseThrow(() -> new RuntimeException("Rin no encontrado, verifica el ID"));

        //se construye la instancia
        Ctl_InventarioLlanta inventario = Ctl_InventarioLlanta.builder()
            .marca(marca)
            .modelo(modelo)
            .rines(rines)
            .num_preciobasico(request.num_preciobasico)
            .fec_alta(request.fec_alta)
            .num_existencia(request.num_existencia)
            .build();

        //se guarda 
        ctlInventarioLlantaRepository.save(inventario);

    }   

}
