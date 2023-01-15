package com.app.myportfolio.repository;

import com.app.myportfolio.entity.Usuario;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("UsuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Serializable>{
    
    public List<Usuario> findByCorreoAndPassword(String correo, String password);
    
}
