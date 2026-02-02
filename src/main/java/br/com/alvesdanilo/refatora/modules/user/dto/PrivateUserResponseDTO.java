package br.com.alvesdanilo.refatora.modules.user.dto;

import br.com.alvesdanilo.refatora.modules.user.model.Role;
import br.com.alvesdanilo.refatora.modules.user.model.SeniorityLevel;
import br.com.alvesdanilo.refatora.modules.user.model.User;

import java.util.UUID;

public record PrivateUserResponseDTO (
        UUID id,
        String firstName,
        String lastName,
        String username,
        SeniorityLevel seniorityLevel,
        Role role
) {

    public PrivateUserResponseDTO(User user) {
        this(user.getId(), user.getFirstName(),
                user.getLastName(), user.getUsername(), user.getSeniorityLevel(), user.getRole());
    }
}

