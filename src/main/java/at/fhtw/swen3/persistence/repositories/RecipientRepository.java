package at.fhtw.swen3.persistence.repositories;


import at.fhtw.swen3.persistence.entities.RecipientEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipientRepository extends JpaRepository<RecipientEntity,Integer> {
}
