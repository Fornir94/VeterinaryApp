package pl.gr.veterinaryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gr.veterinaryapp.model.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
