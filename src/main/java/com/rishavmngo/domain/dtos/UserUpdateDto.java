
package com.rishavmngo.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDto {

	private Long id;

	private String username;

	private String email;

	private String firstname;

	private String lastname;
	private String password;
}
