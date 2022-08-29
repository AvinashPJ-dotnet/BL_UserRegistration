package com.bridgelab.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordDTO {
	private String newPassword;
	private String confirmPassword;
}
