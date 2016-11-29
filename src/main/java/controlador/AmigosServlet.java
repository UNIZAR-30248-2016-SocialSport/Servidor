package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import modelo.Amigo;
import modelo.RepositorioAmigo;
import modelo.RepositorioUsuario;
import modelo.Usuario;

/**
 * Servlet de obtencion de usuaiors
 */
@WebServlet(value = "/amigos", name = "AmigosServlet")
public class AmigosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static RepositorioAmigo repoAmigo = new RepositorioAmigo();
	private static RepositorioUsuario repoUsuario = new RepositorioUsuario();
	private Gson gson = new Gson();

	/**
	 * Metodo para seguir a un usuario.
	 */
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = (HttpSession) req.getSession(); 
		String response = null;		
		String usuario = (String)session.getAttribute("email");
		System.out.println("usuario: "+usuario);
		String amigoSeguido = req.getParameter("emailAmigo");
		System.out.println("amigoSeguido: "+amigoSeguido);
		Date fech = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String fecha = format.format(fech);
		
		Amigo amigo = new Amigo(usuario,amigoSeguido,fecha);
		boolean realizado = repoAmigo.insertarAmigo(amigo);
		//inserta un amigo en la BD
		if (realizado) {
			response = "El amigo se ha insertado correctamente";
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.sendRedirect("muro.html");
		}
		else {
			response = "El amigo no se ha podido insertar";
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.sendRedirect("muro.html");
		}
		setResponse(response, resp);
	}

	/**
	 * Metodo para borrar un amigo del usuario.
	 */
	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String response = null;
		String usuario = req.getParameter("usuario");
		String amigo = req.getParameter("amigo");
		boolean realizado = repoAmigo.borrarAmigo(usuario,amigo);
		//elimina el amigo en la BD
		if (realizado) {
			resp.setStatus(HttpServletResponse.SC_OK);
			response = "El amigo se ha borrado correctamente";
		}
		else {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = "El amigo no se ha podido borrar";
		}
		setResponse(response, resp);
	}

	/**
	 * Metodo para devolver los amigos de un usuario.
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String response = null;
		List<Amigo> amigos = null;
		List<Usuario> amigosUser = new LinkedList<>();
		String email = req.getSession().getAttribute("email").toString();
		
		Usuario buscado = repoUsuario.findUsuario(email);
		System.out.println(email);
		if (email == null || buscado == null) {
			System.out.println("Usuario no encontrado");
			resp.sendRedirect("signup.html");
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		else {
			//devuelve los amigos del usuario
			amigos = repoAmigo.listarAmigos(email);
			if (amigos.isEmpty()) {
				System.out.println("vacio amigos");
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
			else {
				for (Amigo ami : amigos) {
					amigosUser.add(repoUsuario.findUsuario(ami.getAmigo()));
				}
				response = gson.toJson(amigosUser);
				System.out.println("json lleno con amigos: "+response);
				resp.sendRedirect("muro.html");
				resp.setStatus(HttpServletResponse.SC_OK);
			}
		}
		setResponse(response, resp);
		
	}

	/**
	 * Agrega una respuesta a la response
	 * 
	 * @param response
	 *            respuesta
	 * @param resp
	 *            response
	 */
	private void setResponse(String response, HttpServletResponse resp) {
		resp.setContentType("application/json");
		System.out.println(resp.getStatus()+ " hola");
		try {
			PrintWriter out = resp.getWriter();
			out.print(response);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}