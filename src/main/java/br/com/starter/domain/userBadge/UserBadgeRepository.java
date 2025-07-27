package br.com.starter.domain.userBadge;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserBadgeRepository extends JpaRepository<UserBadge, UUID> {

    @Query("SELECT ub FROM UserBadge ub")
    List<UserBadge> findAllList();

    @Query("SELECT ub FROM UserBadge ub")
    Page<UserBadge> findAllPageable(Pageable pageable);

    @Query("SELECT ub FROM UserBadge ub WHERE ub.profile.id = :profileId")
    List<UserBadge> findByProfileId(UUID profileId);

    @Query("SELECT ub FROM UserBadge ub WHERE ub.badge.id = :badgeId")
    List<UserBadge> findByBadgeId(UUID badgeId);

    @Query("SELECT ub FROM UserBadge ub WHERE ub.profile.id = :profileId AND ub.badge.id = :badgeId")
    List<UserBadge> findByProfileIdAndBadgeId(UUID profileId, UUID badgeId);
}
