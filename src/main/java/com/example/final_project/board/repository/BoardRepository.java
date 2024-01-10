package com.example.final_project.board.repository;

import com.example.final_project.board.domain.Board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
  List<Board> findByTitleContaining(String keyword);

}
