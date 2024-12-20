package com.sensorium.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sensorium.api.dto.request.ZoneDtoReq;
import com.sensorium.api.dto.response.ResponseBody;
import com.sensorium.api.dto.response.ZoneDtoResp;
import com.sensorium.api.entity.Zone;
import com.sensorium.api.exception.ResourceNotFoundException;
import com.sensorium.api.mapper.ZoneMapper;
import com.sensorium.api.repository.ZoneRepository;
import com.sensorium.api.service.IZoneService;

@Service
public class ZoneService implements IZoneService {

	@Autowired
	private ZoneRepository repository;

	@Autowired
	private ZoneMapper mapper;

	@Override
	public Zone getById(String id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
	}

	@Override
	public List<ZoneDtoResp> getAll(Integer page) {
		int size = 3;
		Pageable pageable = PageRequest.of(page, size);

		List<Zone> zones = repository.findAll(pageable).getContent();

		return mapper.entitiesToDtos(zones);
	}

	@Override
	public ZoneDtoResp getDetails(String id) {
		return mapper.entityToDto(getById(id));
	}

	@Override
	public ZoneDtoResp create(ZoneDtoReq dto) {
		Zone zone = mapper.DtoToentity(dto);

		return mapper.entityToDto(repository.save(zone));
	}

	@Override
	public ZoneDtoResp update(ZoneDtoReq dto, String id) {
		Zone zone = getById(id);

		zone.setName(dto.getName());
		zone.setLocation(dto.getLocation());
		zone.setType(dto.getType());

		return mapper.entityToDto(repository.save(zone));
	}

	@Override
	public ResponseBody delete(String id) {
		Zone zone = getById(id);
		repository.delete(zone);
		return ResponseBody.builder().message("Zone deleted successfully").build();
	}

}
