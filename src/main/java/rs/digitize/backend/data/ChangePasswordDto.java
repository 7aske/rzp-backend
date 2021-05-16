package rs.digitize.backend.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static rs.digitize.backend.util.StringUtils.falsy;

@Setter
@NoArgsConstructor
public class ChangePasswordDto {
	private String password;
	private String confirm;
	private String previous;

	@JsonIgnore
	public boolean isValid() {
		if (falsy(confirm) || falsy(password))
			return false;
		return password.equals(confirm);
	}

	public String getConfirm() {
		return confirm;
	}

	public String getPassword() {
		return password;
	}

	public String getPrevious() {
		return previous;
	}

}
