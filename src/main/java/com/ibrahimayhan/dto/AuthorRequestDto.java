package com.ibrahimayhan.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequestDto {

	//save veya update ederken id url ile gelecek request dto ile değil
	@NotEmpty(message = "Author cannot be emty")
	private String authorNameSurname;
}
