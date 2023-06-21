package com.example.project_templates.adapter.repository;

import com.example.project_templates.domain.entity.GreatQuotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreatQuotesRepository extends JpaRepository<GreatQuotes, Long> {
}
