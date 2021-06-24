package ru.fadesml.bank.repository;

import org.springframework.stereotype.Repository;
import ru.fadesml.bank.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CardRepository extends JpaRepository<Card, String> {
}
