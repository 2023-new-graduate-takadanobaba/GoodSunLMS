package com.reality.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reality.entity.Attendance;
import com.reality.entity.User;
public interface AttendanceRepository extends JpaRepository<Attendance, Integer>{
	Attendance findByDate(Date date);
	List<Attendance> findByUser(User user);
	List<Attendance> findByDateAndStartTime(Date date, String startTime);
	void deleteByDateAndStartTimeAndUser(Date date, String startTime, User user);
	List<Attendance> findByDateContaining(Date date);
}
