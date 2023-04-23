/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.nisum.usuario.repository;

import com.nisum.usuario.model.Usuario;
import org.springframework.data.repository.CrudRepository;


public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
        public Usuario findByEmail(String email);
	
	public Usuario findById(String id);

	public Usuario findByName(String name);
	 
}
