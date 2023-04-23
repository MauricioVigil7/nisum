
package com.nisum.usuario.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@Data
public class UserResponse implements Serializable {

	private UUID id;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date created;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date modified;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date last_login;
	private String token;
	private boolean isactive;
	
	
	public void mapper(Usuario userCreated) {
		this.id = userCreated.getId();
		this.created= userCreated.getCreated();
		this.created= userCreated.getCreated();
		this.last_login= userCreated.getCreated();
		this.token = userCreated.getToken();
		this.isactive = userCreated.getIsactive();
	}

}
