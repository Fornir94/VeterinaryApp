package pl.gr.veterinaryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gr.veterinaryapp.model.entity.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}
