package antho.demo_jwt.llantas.cat_movimientosinventario;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovimientoInventarioService {

    private final MovimientoInventarioRepository movimientoInventarioRepository;

    public List<MovimientoInventario> getAllMovimientosInventario() {
        return movimientoInventarioRepository.findAll();
    }

    public void registerMovimientoInventario(MovimientoInventario request) {
    
        
        //Guardar
        movimientoInventarioRepository.save(request);
    }

}
