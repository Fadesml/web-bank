package ru.fadesml.bank.repository;

import org.springframework.data.repository.CrudRepository;
import ru.fadesml.bank.models.Contribution;

public interface ContributionRepository extends CrudRepository<Contribution, String> {
}
