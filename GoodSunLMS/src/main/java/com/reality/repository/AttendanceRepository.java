package com.reality.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reality.entity.Attendance;
public interface AttendanceRepository extends JpaRepository<Attendance, Integer>{
	Attendance findByDate(Date date);
}
