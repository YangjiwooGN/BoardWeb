package com.hyundai.cloud.repository;

import com.hyundai.cloud.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

}
