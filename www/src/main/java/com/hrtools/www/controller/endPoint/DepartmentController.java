package com.hrtools.www.controller.endPoint;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.hrtools.www.controller.request.DepartmentRequest;
import com.hrtools.www.controller.response.DepartmentResponse;
import com.hrtools.www.service.DepartmentService;

import javax.persistence.EntityNotFoundException;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/department")
    public List<DepartmentResponse> listDepartments() throws Exception {
        return departmentService.listDepartments();
    }

    @PostMapping("/department")
    public ResponseEntity<DepartmentResponse> createDepartment(@RequestBody @Validated DepartmentRequest departmentRequest,
                                                               UriComponentsBuilder uriBuilder) throws Exception {
        DepartmentResponse department = departmentService.createDepartment(departmentRequest);
        URI uri = uriBuilder.path("/department/{id}").buildAndExpand(department.getId()).toUri();
        return ResponseEntity.created(uri).body(department);
    }

    @PutMapping("/department/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartment(@RequestBody @Validated DepartmentRequest departmentRequest, @PathVariable Long id) throws Exception {
        try {
            return ResponseEntity.ok(departmentService.updateDepartment(departmentRequest, id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<DepartmentResponse> findDepartment(@PathVariable Long id) throws Exception {
        try {
            return ResponseEntity.ok(departmentService.findDepartment(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
