package com.hrtools.www.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrtools.www.controller.request.PositionRequest;
import com.hrtools.www.controller.response.PositionResponse;
import com.hrtools.www.etl.PositionETL;
import com.hrtools.www.model.Position;
import com.hrtools.www.model.Department;
import com.hrtools.www.repository.DepartmentRepository;
import com.hrtools.www.repository.PositionRepository;
import javax.transaction.Transactional;

@Service
public class PositionService {

	@Autowired
	private PositionRepository positionRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	public PositionResponse createPosition(PositionRequest positionRequest) throws Exception {
		try {
			Department department = null;
			Optional<Department> departmentOpitional = departmentRepository.findById(positionRequest.getDepartment());
			if (departmentOpitional.isPresent()) {
				department = departmentOpitional.get();
			}

			return PositionETL.convertPositionToPositionResponse(
					positionRepository.save(
							PositionETL.convertPositionRequestToPosition(positionRequest, new Position(), department)),
					true);
		} catch (Exception e) {
			throw new Exception("Method: createPosition | Reason: " + e.getMessage(), e);
		}
	}

	@Transactional
	public PositionResponse updatePosition(PositionRequest positionRequest, Long id)
			throws Exception {
		try {
			Optional<Position> positionOptional = positionRepository.findById(id);
			if (positionOptional.isPresent()) {
				Position position = positionOptional.get();
				Department department = null;
				Optional<Department> departmentOpitional = departmentRepository.findById(positionRequest.getDepartment());
				if (departmentOpitional.isPresent()) {
					department = departmentOpitional.get();
				}
				PositionETL.convertPositionRequestToPosition(positionRequest, position, department);
				return PositionETL.convertPositionToPositionResponse(position, true);
			}
			throw new Exception("Position not found");
		} catch (Exception e) {
			throw new Exception("Method: updatePosition | Reason: " + e.getMessage(), e);
		}
	}

	public List<PositionResponse> listPositions() throws Exception {
		try {
			return positionRepository.findAll().stream().map(position -> {
				try {
					return PositionETL.convertPositionToPositionResponse(position, true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}).toList();
		} catch (Exception e) {
			throw new Exception("Method: listPositions | Reason: " + e.getMessage(), e);
		}
	}

	public PositionResponse findPosition(Long id) throws Exception {
		try {
			Optional<Position> positionOptional = positionRepository.findById(id);
			if (positionOptional.isPresent()) {
				return PositionETL.convertPositionToPositionResponse(positionOptional.get(), true);
			}
			throw new Exception("Position not found");
		} catch (Exception e) {
			throw new Exception("Method: findPosition | Reason: " + e.getMessage(), e);
		}
	}
}
