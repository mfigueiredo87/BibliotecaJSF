package ao.com.biblioteca.service;

import java.io.Serializable;

import javax.inject.Inject;

import ao.com.biblioteca.dao.UsuariosDAO;
import ao.com.biblioteca.modelo.Usuario;
import ao.com.biblioteca.util.jpa.Transactional;


public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuariosDAO usuarios;

	@Transactional
	public Usuario salvar(Usuario usuario) {

		Usuario usuarioExistente = this.usuarios.porEmail(usuario.getEmail());

		if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
			throw new NegocioException("Já existe um usuário com o e-mail informado.");
		}

		return this.usuarios.guardar(usuario);
	}

}
