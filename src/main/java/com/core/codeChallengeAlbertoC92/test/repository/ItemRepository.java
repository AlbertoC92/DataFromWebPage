package com.core.codeChallengeAlbertoC92.test.repository;

import com.core.codeChallengeAlbertoC92.test.data.DbItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<DbItem, Integer> {
    DbItem findItemByTitle(String title);
}
