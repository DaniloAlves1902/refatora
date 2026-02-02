package br.com.alvesdanilo.refatora.modules.user.dto;

import br.com.alvesdanilo.refatora.modules.user.model.SeniorityLevel;
import br.com.alvesdanilo.refatora.modules.user.model.User;

import java.util.UUID;

public record PublicUserResponseDTO(
        UUID id,
        String firstName,
        String lastName,
        String username,
        String email,
        SeniorityLevel seniorityLevel
) {

    public PublicUserResponseDTO(User user) {
        this(user.getId(), user.getFirstName(),
                user.getLastName(), user.getUsername(), user.getEmail(), user.getSeniorityLevel());
    }
}
