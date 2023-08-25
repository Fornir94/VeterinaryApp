package pl.gr.veterinaryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gr.veterinaryapp.model.entity.VetAppUser;

import java.util.Optional;

@Repository
public interface VetAppUserRepository extends JpaRepository<VetAppUser, Long> {

    Optional<VetAppUser> findByUsername(String username);
}
