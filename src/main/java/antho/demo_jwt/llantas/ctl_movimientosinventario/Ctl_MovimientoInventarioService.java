package antho.demo_jwt.llantas.ctl_movimientosinventario;

import java.util.List;

import org.springframework.stereotype.Service;

import antho.demo_jwt.llantas.ctl_inventariollantas.Ctl_InventarioLlanta;
import antho.demo_jwt.llantas.ctl_inventariollantas.Ctl_InventarioLlantaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Ctl_MovimientoInventarioService {

    private final Ctl_MovimientoInventarioRepository ctlMovimientoInventarioRepository;
    private final Ctl_InventarioLlantaRepository ctlInventarioLlantaRepository;

    public List<Ctl_MovimientoInventario> getAllMovimientosInventario() {
        return ctlMovimientoInventarioRepository.findAll();
    }

    public void registerMovimientoInventario(Ctl_MovimientoInventarioDTO request) {
        //obtener llanta
        Ctl_InventarioLlanta llanta = ctlInventarioLlantaRepository.findById(request.id_llanta)
            .orElseThrow(() -> new RuntimeException("Llanta no encontrada, verifica el ID"));
        //construir instancia
        Ctl_MovimientoInventario movimiento = Ctl_MovimientoInventario.builder()
            .llanta(llanta)
            .num_precio(request.num_precio)
            .fec_movimiento(request.fec_movimiento)
            .num_empleado(request.num_empleado)
            .opc_activo(request.opc_activo)
            .build();     

        //guardar
        ctlMovimientoInventarioRepository.save(movimiento);
    }

}
