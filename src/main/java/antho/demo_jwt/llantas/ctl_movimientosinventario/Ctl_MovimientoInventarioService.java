package antho.demo_jwt.llantas.ctl_movimientosinventario;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Ctl_MovimientoInventarioService {

    private final Ctl_MovimientoInventarioRepository ctlMovimientoInventarioRepository;

    public List<Ctl_MovimientoInventario> getAllMovimientosInventario() {
        return ctlMovimientoInventarioRepository.findAll();
    }

    public void registerMovimientoInventario(Ctl_MovimientoInventario request) {
        //guardar
        ctlMovimientoInventarioRepository.save(request);
    }

}
