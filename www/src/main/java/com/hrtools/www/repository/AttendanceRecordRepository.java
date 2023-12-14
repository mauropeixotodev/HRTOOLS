package com.hrtools.www.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrtools.www.model.AttendanceRecord;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {

	 @Query("SELECT ar FROM AttendanceRecord ar WHERE ar.status = :status " +
	           "AND ar.dayDate <= :currentDate " +
	           "AND ar.employee.id = :employeeId")
	    Optional<AttendanceRecord> findOneByStatusAndPastDayDateAndEmployee(
	        @Param("status") int status,
	        @Param("currentDate") Date currentDate,
	        @Param("employeeId") Long employeeId
	    );
}
