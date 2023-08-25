package pl.gr.veterinaryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gr.veterinaryapp.model.entity.BlockedToken;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
public interface BlockedTokenRepository extends JpaRepository<BlockedToken, Long> {

    Optional<BlockedToken> findByAuthToken(String authToken);

    void deleteAllByExpirationTimeBefore(OffsetDateTime now);
}
