package com.nisum.usuario.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name="t_user")
@Schema(name = "Usuario", description = "Model represent a usuario on database")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    @Schema(name = "id", required = false,example = "0", 
          defaultValue = "0", 
          description = "Unique Id representa la clave de la tabla de usuarios")
    private UUID id;

    @JsonProperty("name")
    @NotBlank(message="El nombre de usuario no puede estar vacío")
    @Size(min = 10, max = 50, message = "El nombre debe estar entre 10 y 50 caracteres")
    private String name;

    @JsonProperty("email")
    @NotBlank(message="Email de usuario no puede estar vacío")
    @Size(min = 10, max = 100, message = "El email debe estar entre 10 y 100 caracteres")
    @Email(message = "Email no corresponde al dominio", regexp = "([a-z]+@dominio.cl)")
    private String email;
    
    @JsonProperty("password")
    @NotBlank(message="Password de usuario no puede estar vacío")
    @Size(min = 8, max = 100, message = "El password debe estar entre 8 y 100 caracteres")
    private String password;
    
	@Column(name = "created")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date created;
	
	@Column(name = "modified")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date modified;
	
	@Column(name = "last_login")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date last_login;
	
    @JsonProperty("token")
    private String token;
	
	@Column(name = "isactive")
	private Boolean isactive;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Telefono> phones;

    

}

