package DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Tema;

public interface TemaDAO extends JpaRepository<Tema, Integer> {
}