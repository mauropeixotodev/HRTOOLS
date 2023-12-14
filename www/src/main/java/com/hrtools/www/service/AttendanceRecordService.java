package com.hrtools.www.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.hrtools.www.controller.request.AttendanceRecordRequest;
import com.hrtools.www.controller.response.AttendanceRecordResponse;
import com.hrtools.www.etl.AttendanceRecordETL;
import com.hrtools.www.model.AttendanceRecord;
import com.hrtools.www.model.Employee;
import com.hrtools.www.repository.AttendanceRecordRepository;
import com.hrtools.www.repository.EmployeeRepository;

@Service
public class AttendanceRecordService {

    @Autowired
    AttendanceRecordRepository attendanceRecordRepository;
    
    @Transactional
    public AttendanceRecordResponse createAttendanceRecord() throws Exception {
        try {
        	
        	
        	 Employee employee = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	 if(employee != null) {
        		 Optional<AttendanceRecord> attendanceRecordPast =	attendanceRecordRepository.findOneByStatusAndPastDayDateAndEmployee(1, new Date(), employee.getId());
        		if(attendanceRecordPast.isPresent()) {
        			
        			throw new Exception("Usuário já realizou o checkOut diário");
        		}
             
        	 Optional<AttendanceRecord> attendanceRecord =	attendanceRecordRepository.findOneByStatusAndPastDayDateAndEmployee(0, new Date(), employee.getId());
        	 
        	 if(attendanceRecord.isEmpty()) {
        		 AttendanceRecord record = AttendanceRecord.builder().dayDate(new Date()).clockIn(new Date()).status(0).employee(employee).build(); // Ajuste conforme necessário
                 return AttendanceRecordETL.convertAttendanceRecordToAttendanceRecordResponse(
                         attendanceRecordRepository.save(record));
        		 
        	 }else {
        		 AttendanceRecord attendance =	 attendanceRecord.get();
        		 attendance.setStatus(1);
        		 attendance.setClockOut(new Date());
        		 return AttendanceRecordETL.convertAttendanceRecordToAttendanceRecordResponse(
                         attendanceRecordRepository.save(attendance)) ;
        		 
        	 }
        
        }

        	throw new Exception("Usuário não encontrado!");
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Transactional
    public AttendanceRecordResponse updateAttendanceRecord(AttendanceRecordRequest request, Long id) throws Exception {
        try {
            Optional<AttendanceRecord> recordOptional = attendanceRecordRepository.findById(id);
            if (recordOptional.isPresent()) {
                AttendanceRecord record = recordOptional.get();
                AttendanceRecordETL.convertAttendanceRecordRequestToAttendanceRecord(request, record.getEmployee());
                return AttendanceRecordETL.convertAttendanceRecordToAttendanceRecordResponse(record);
            }
            throw new Exception("AttendanceRecord não encontrado");
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public List<AttendanceRecordResponse> listAttendanceRecords() throws Exception {
        try {
            return attendanceRecordRepository.findAll().stream().map(record -> {
                try {
                    return AttendanceRecordETL.convertAttendanceRecordToAttendanceRecordResponse(record);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }).toList();
        } catch (Exception e) {
            throw new Exception("Método: listAttendanceRecords | Motivo: " + e.getMessage());
        }
    }

    public AttendanceRecordResponse findAttendanceRecord(Long id) throws Exception {
        try {
            Optional<AttendanceRecord> recordOptional = attendanceRecordRepository.findById(id);
            if (recordOptional.isPresent()) {
                return AttendanceRecordETL.convertAttendanceRecordToAttendanceRecordResponse(recordOptional.get());
            }
            throw new Exception("AttendanceRecord não encontrado");
        } catch (Exception e) {
            throw new Exception("Método: findAttendanceRecord | Motivo: " + e.getMessage());
        }
    }
}
