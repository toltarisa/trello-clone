package com.isatoltar.trelloclone.card.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    @Query("SELECT c FROM Card c WHERE c.id = ?1 AND c.taskList.id = ?2")
    Optional<Card> getByIdAndListId(Integer id, Integer listId);

    @Query("SELECT c FROM Card c WHERE c.taskList.id = ?1")
    Optional<List<Card>> getCardsOfList(Integer listId);
}