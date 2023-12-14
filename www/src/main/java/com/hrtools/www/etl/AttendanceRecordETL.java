package com.hrtools.www.etl;

import java.util.Date;

import com.hrtools.www.controller.request.AttendanceRecordRequest;
import com.hrtools.www.controller.response.AttendanceRecordResponse;
import com.hrtools.www.model.AttendanceRecord;
import com.hrtools.www.model.Employee;

public class AttendanceRecordETL {

    public static AttendanceRecord convertAttendanceRecordRequestToAttendanceRecord(AttendanceRecordRequest request, Employee employee) throws Exception {
        try {
            AttendanceRecord record = new AttendanceRecord();
            record.setDayDate(new Date());
            record.setClockIn(new Date());
            record.setClockOut(new Date());// Sets current date, adjust as needed
            record.setStatus(request.getStatus());
            record.setEmployee(employee); // Assuming Employee object is provided
            // clockIn and clockOut are managed by the system, presumably
            return record;
        } catch (Exception e) {
            throw new Exception("Method: convertAttendanceRecordRequestToAttendanceRecord | Reason: " + e.getMessage(), e);
        }
    }

    public static AttendanceRecordResponse convertAttendanceRecordToAttendanceRecordResponse(AttendanceRecord record) throws Exception {
        try {
            return AttendanceRecordResponse.builder()
                    .id(record.getId())
                    .dayDate(record.getDayDate())
                    .clockIn(record.getClockIn())
                    .clockOut(record.getClockOut())
                    .status(record.getStatus())
                    .employee(record.getEmployee().getFullname())
                    .build();
        } catch (Exception e) {
            throw new Exception("Method: convertAttendanceRecordToAttendanceRecordResponse | Reason: " + e.getMessage(), e);
        }
    }
}
