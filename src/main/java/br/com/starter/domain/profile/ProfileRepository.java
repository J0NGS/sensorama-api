package br.com.starter.domain.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    @Query("SELECT p FROM Profile p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Profile> findByNameContainingIgnoreCase(String name);

    @Query("SELECT p FROM Profile p WHERE LOWER(p.name) = LOWER(:username)")
    List<Profile> findByUsernameContainingIgnoreCase(String username);
}
