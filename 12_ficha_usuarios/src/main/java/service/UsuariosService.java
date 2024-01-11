package service;

import java.util.ArrayList;
import java.util.List;

import model.Ficha;
import model.Usuario;

public class UsuariosService {
	List<Usuario> usuarios=new ArrayList<>(List.of(
			new Usuario("user1","pwd1"),
			new Usuario("user2","pwd2"),
			new Usuario("user3","pwd3"),
			new Usuario("user4","pwd4")
			));
	List<Ficha> fichas=new ArrayList<>(List.of(
			new Ficha("user1",65,"user1@gmail.com"),
			new Ficha("user2",33,"user2@gmail.com"),
			new Ficha("user3",29,"user3@gmail.com"),
			new Ficha("user4",44,"user4@gmail.com")
			));
	public boolean autenticar(String usuario, String pwd) {
		for(Usuario u:usuarios) {
			if(u.getUsuario().equals(usuario)&&u.getPassword().equals(pwd)) {
				return true;
			}
		}
		return false;
		
		/*return usuarios.stream()
				.anyMatch(u->u.getUsuario().equals(usuario)&&u.getPassword().equals(pwd));*/
	}
	public Ficha fichaUsuario(String usuario) {
		/*for(Ficha f:fichas) {
			if(f.getUsuario().equals(usuario)) {
				return f;
			}
		}
		return null;*/
		return fichas.stream() //Stream<Ficha>
				.filter(f->f.getUsuario().equals(usuario)) //Stream<Ficha>;
				.findFirst() //Optional<Ficha>
				.orElse(null);
	}
}
