package com.hrtools.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrtools.www.model.AttendanceRecord;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {

}
