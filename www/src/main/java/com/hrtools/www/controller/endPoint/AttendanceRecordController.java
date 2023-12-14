package com.hrtools.www.controller.endPoint;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.hrtools.www.controller.request.AttendanceRecordRequest;
import com.hrtools.www.controller.response.AttendanceRecordResponse;
import com.hrtools.www.service.AttendanceRecordService;

import javax.persistence.EntityNotFoundException;

@RestController
public class AttendanceRecordController {

    @Autowired
    private AttendanceRecordService attendanceRecordService;

    @GetMapping("/attendanceRecord")
    public List<AttendanceRecordResponse> listAttendanceRecords() throws Exception {
        return attendanceRecordService.listAttendanceRecords();
    }

    @PostMapping("/attendanceRecord")
    public ResponseEntity<AttendanceRecordResponse> createAttendanceRecord(UriComponentsBuilder uriBuilder) throws Exception {
        AttendanceRecordResponse record = attendanceRecordService.createAttendanceRecord();
        URI uri = uriBuilder.path("/attendanceRecord/{id}").buildAndExpand(record.getId()).toUri();
        return ResponseEntity.created(uri).body(record);
    }

    @PutMapping("/attendanceRecord/{id}")
    public ResponseEntity<AttendanceRecordResponse> updateAttendanceRecord(@RequestBody @Validated AttendanceRecordRequest request,
                                                                           @PathVariable Long id) throws Exception {
        try {
            return ResponseEntity.ok(attendanceRecordService.updateAttendanceRecord(request, id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/attendanceRecord/{id}")
    public ResponseEntity<AttendanceRecordResponse> findAttendanceRecord(@PathVariable Long id) throws Exception {
        try {
            return ResponseEntity.ok(attendanceRecordService.findAttendanceRecord(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
