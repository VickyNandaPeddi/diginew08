package com.aashdit.digiverifier.config.superadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.digiverifier.config.superadmin.model.Color;

public interface ColorRepository extends JpaRepository<Color, Long> {

	Color findByColorCode(String colorCode);

}
