package com.example.dklearn.logout.repository;


import com.example.dklearn.logout.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
   Token findFirstByTokenValue(String token);
}
