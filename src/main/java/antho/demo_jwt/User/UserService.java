package antho.demo_jwt.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public UserDTO getUserDTOByUsername(String username) {
        return userRepository.findByUsername(username)
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

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
            .map(user -> new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getLastname(),
                user.getFirstname(),
                user.getCountry(),
                user.getRole().name()
            ))
            .collect(Collectors.toList());
    }       

    public UserDTO updateUser(String username, UpdateUserDTO updateUserDTO) {
        User user = userRepository.findByUsername(username)
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
    public void updateUserRole(String username, String role) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        user.setRole(Role.valueOf(role)); // Cambiar el rol del usuario
        userRepository.save(user); // Guardar los cambios
    }

    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public List<UserDTO> searchUsers(String searchTerm) {
        return userRepository.findAll().stream()
                .filter(user -> user.getUsername().toLowerCase().contains(searchTerm.toLowerCase()) ||
                        user.getLastname().toLowerCase().contains(searchTerm.toLowerCase()) ||
                        user.getFirstname().toLowerCase().contains(searchTerm.toLowerCase()) ||
                        user.getCountry().toLowerCase().contains(searchTerm.toLowerCase()))
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getLastname(),
                        user.getFirstname(),
                        user.getCountry(),
                        user.getRole().name()
                ))
                .collect(Collectors.toList());
    }


}
