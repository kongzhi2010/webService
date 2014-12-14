package husd.wsi.back.pojo;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {
	
	@Size(min=3,max=20,message="username shoud be 3-20 length")
	@Pattern(regexp="^[a-zA-z0-9]+$",message="username must be alphanumber without spaces")
	private String username;
	
	private String password;
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
