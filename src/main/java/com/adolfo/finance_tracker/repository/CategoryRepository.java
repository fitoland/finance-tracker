package com.adolfo.finance_tracker.repository;

import com.adolfo.finance_tracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
