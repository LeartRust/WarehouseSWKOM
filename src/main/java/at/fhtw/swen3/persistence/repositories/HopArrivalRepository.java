package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HopArrivalRepository extends JpaRepository<HopArrivalEntity,Integer> {
    HopArrivalEntity findByCodeAndId(String code, Integer Id);
}
