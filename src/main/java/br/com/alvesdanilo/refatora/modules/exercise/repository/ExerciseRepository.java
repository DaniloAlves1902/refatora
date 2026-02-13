package br.com.alvesdanilo.refatora.modules.exercise.repository;

import br.com.alvesdanilo.refatora.modules.exercise.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {

    Optional<Exercise> findBySlug(String slug);

    boolean existsBySlug(String slug);
}