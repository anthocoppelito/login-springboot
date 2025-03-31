package antho.demo_jwt.User;

import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public UserDTO getUserDTOById(Integer id) {
        return userRepository.findById(id)
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getLastname(),
                        user.getFirstname(),
                        user.getCountry(),
                        user.getRole().name()
                ))
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public UserDTO updateUser(Integer id, UpdateUserDTO updateUserDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    
        // Actualizar los campos permitidos
        user.setLastname(updateUserDTO.getLastname());
        user.setFirstname(updateUserDTO.getFirstname());
        user.setCountry(updateUserDTO.getCountry());
    
        // Guardar los cambios en la base de datos
        userRepository.save(user);
    
        // Retornar el usuario actualizado como UserDTO
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getLastname(),
                user.getFirstname(),
                user.getCountry(),
                user.getRole().name()
        );
    }

    @Transactional
    public void updateUserRole(Integer id, String role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        user.setRole(Role.valueOf(role)); // Cambiar el rol del usuario
        userRepository.save(user); // Guardar los cambios
    }


}
