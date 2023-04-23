/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.nisum.usuario.controller;

import com.nisum.usuario.service.UsuarioService;
import com.nisum.usuario.common.ErrorMessage;
import com.nisum.usuario.model.UserResponse;
import com.nisum.usuario.model.Usuario;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author HP
 */
@RestController
@RequestMapping("/nisum")
public class UsuarioController {
    public static final String SECRET = Base64Utils.encodeToString("nisum.04.2023".getBytes());
	public static final long EXPIRATION_DATE = 14000000L;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	
    @Autowired
    private UsuarioService userService;
    
    @Autowired
    private ErrorMessage errorMessage;
    
    @Autowired
    private UserResponse userResponse;

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "User Nisum services";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<Usuario> list() {
        List<Usuario> users = userService.list();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes= {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ApiOperation(value ="Create user", response = UserResponse.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message="Usuario creado correctamente"),
    		@ApiResponse(code = 401, message="Token incorrecto")
    })
    public ResponseEntity<Usuario> create(@Valid @RequestBody Usuario user, BindingResult br, @RequestHeader Map<String,String> headers) {
    	try {
    		if(br.hasErrors()){
    			String errors = "";
    			for(ObjectError temp: br.getAllErrors()){
    				errors += temp.getDefaultMessage()+". ";
    			}
    			errorMessage.setMensaje(errors);
        		return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
    		}
    		Usuario userDb = userService.findByEmail(user.getEmail());
    		if(userDb!=null) {
    			errorMessage.setMensaje("Correo ya está registrado");
        		return new ResponseEntity(errorMessage, HttpStatus.OK);
    		}else{
        		user.setCreated(new Date());
        		user.setToken(headers.get("authorization"));
        		user.setLast_login(new Date());
        		user.setIsactive(true);
        		Usuario userCreated = userService.create(user);
        		userResponse.mapper( userCreated );
                return new ResponseEntity(userResponse, HttpStatus.CREATED);       			
    		}
    	}catch (Exception e) {
    		errorMessage.setMensaje(e.getMessage());
    		return new ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
