package antho.demo_jwt.llantas.cat_movimientosinventario;

import java.time.LocalDateTime;
import java.time.ZoneId;
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

        if (request.getFec_alta() == null) {
            request.setFec_alta(LocalDateTime.now(ZoneId.of("America/Mazatlan")));
        }
        if (request.getOpc_activo() == null) {
            request.setOpc_activo(true);
        }
    
        
        //Guardar
        movimientoInventarioRepository.save(request);
    }

}
