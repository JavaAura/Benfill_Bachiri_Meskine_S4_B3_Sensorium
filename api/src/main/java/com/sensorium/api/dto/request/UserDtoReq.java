package com.sensorium.api.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserDtoReq {

	@NotNull(message = "Name is required")
	@NotBlank(message = "Name is required")
	private String name;
	@NotNull(message = "Username is required")
	@NotBlank(message = "Username is required")
	private String username;
	@NotNull(message = "Password is required")
	@NotBlank(message = "Password is required")
	private String password;
}
