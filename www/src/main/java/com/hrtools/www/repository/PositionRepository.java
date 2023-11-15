package com.hrtools.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrtools.www.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {

}
