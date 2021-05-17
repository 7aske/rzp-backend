package rs.digitize.backend.data;

import lombok.Data;

@Data
public class RegisterUserDto {
	private String username;
	private String password;
	private String confirm;
	private String email;
	private String firstName;
	private String lastName;
}
