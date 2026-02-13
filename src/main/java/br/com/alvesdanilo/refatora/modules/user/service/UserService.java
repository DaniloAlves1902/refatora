package br.com.alvesdanilo.refatora.modules.user.service;

import br.com.alvesdanilo.refatora.modules.user.dto.CreateUserDTO;
import br.com.alvesdanilo.refatora.modules.user.dto.PrivateUserResponseDTO;
import br.com.alvesdanilo.refatora.modules.user.dto.PublicUserResponseDTO;
import br.com.alvesdanilo.refatora.modules.user.dto.UpdateUserDTO;
import br.com.alvesdanilo.refatora.modules.user.model.Role;
import br.com.alvesdanilo.refatora.modules.user.model.User;
import br.com.alvesdanilo.refatora.modules.user.repository.UserRepository;
import br.com.alvesdanilo.refatora.shared.exception.user.EmailAlreadyExistsException;
import br.com.alvesdanilo.refatora.shared.exception.user.UserNotFoundException;
import br.com.alvesdanilo.refatora.shared.exception.user.UsernameAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public Page<PublicUserResponseDTO> publicListAll(Pageable pageable) {
        return this.userRepository.findAll(pageable)
                .map(PublicUserResponseDTO::new);
    }

    public Page<PrivateUserResponseDTO> privateListAll(Pageable pageable) {
        return this.userRepository.findAll(pageable)
                .map(PrivateUserResponseDTO::new);
    }

    public PublicUserResponseDTO publicFindById(UUID id) {
        return this.userRepository.findById(id)
                .map(PublicUserResponseDTO::new)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public PrivateUserResponseDTO privateFindById(UUID id) {
        return this.userRepository.findById(id)
                .map(PrivateUserResponseDTO::new)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public PublicUserResponseDTO findByUsername(String username){
        return this.userRepository.findByUsername(username)
                .map(PublicUserResponseDTO::new)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Transactional
    public PrivateUserResponseDTO createCommonUser(CreateUserDTO data) {
        User user = saveNewUser(data, Role.USER);
        return new PrivateUserResponseDTO(user);
    }

    @Transactional
    public PrivateUserResponseDTO createAdminUser(CreateUserDTO data) {
        User user = saveNewUser(data, Role.ADMIN);
        return new PrivateUserResponseDTO(user);
    }

    @Transactional
    public PrivateUserResponseDTO update(UUID id, UpdateUserDTO data) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        validateUniqueDataForUpdate(user, data);

        user.setFirstName(data.firstName());
        user.setLastName(data.lastName());
        user.setUsername(data.username());
        user.setEmail(data.email());
        user.setSeniorityLevel(data.seniorityLevel());

        this.userRepository.save(user);
        return new PrivateUserResponseDTO(user);
    }

    @Transactional
    public void delete(UUID id) {
        if (!this.userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found");
        }
        this.userRepository.deleteById(id);
    }

    private User saveNewUser(CreateUserDTO data, Role role) {
        validateUsernameAndEmail(data.username(), data.email());

        User newUser = new User();
        newUser.setFirstName(data.firstName());
        newUser.setLastName(data.lastName());
        newUser.setUsername(data.username());
        newUser.setEmail(data.email());
        newUser.setSeniorityLevel(data.seniorityLevel());
        newUser.setRole(role);

        var passwordHash = this.passwordEncoder.encode(data.password());
        newUser.setPassword(passwordHash);

        return this.userRepository.save(newUser);
    }

    private void validateUsernameAndEmail(String username, String email) {

        if (userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already in use");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException("Email already in use");
        }
    }

    private void validateUniqueDataForUpdate(User user, UpdateUserDTO data) {
        if (!user.getEmail().equals(data.email()) && this.userRepository.findByEmail(data.email()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already in use");
        }

        if (!user.getUsername().equals(data.username()) && this.userRepository.findByUsername(data.username()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already in use");
        }
    }


}
