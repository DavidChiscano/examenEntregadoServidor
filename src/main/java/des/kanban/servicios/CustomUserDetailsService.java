package des.kanban.servicios;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import des.kanban.entidades.Rol;
import des.kanban.entidades.Usuario;
import des.kanban.modelo.UsuarioRepository;
@Transactional
@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UsuarioRepository usuarioJPA;
	@Override
	public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
		Usuario usuario = usuarioJPA.findByNombreUsuario(nombre);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Rol rol : usuario.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(rol.getNombre_rol()));
		}
		return new org.springframework.security.core.userdetails.User(usuario.getNombre_usuario(), usuario.getPassword(),
				grantedAuthorities);
	}
}
