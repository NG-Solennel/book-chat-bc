package com.sol.bookchat.repository;

import com.sol.bookchat.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite ,String> {
}
