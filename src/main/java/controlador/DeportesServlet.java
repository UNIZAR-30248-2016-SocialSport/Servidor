package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Deporte;
import modelo.RepositorioDeporte;

import com.google.gson.Gson;

/**
 * Servlet de obtencion de usuaiors
 */
@WebServlet(value = "/deportes", name = "DeportesServlet")
public class DeportesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static RepositorioDeporte repo = new RepositorioDeporte();
	private Gson gson = new Gson();
	/**
	 * M�todo para a�adir usuarios a la BD a trav�s del cliente.
	 */
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String response = null;
		String email = req.getParameter("email");
		String deporte = req.getParameter("deporte");
		boolean realizado = repo.suscribirseDeporte(deporte,email);
		if (realizado) {
			resp.setStatus(HttpServletResponse.SC_OK);
			response = "El usuario se ha suscrito correctamente al deporte";
		}
		else {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = "El usuario no se ha podido suscribir al deporte";
		}
		setResponse(response, resp);
	}
	
	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String response = null;
		String email = req.getParameter("email");
		String deporte = req.getParameter("deporte");
		boolean realizado = repo.darseDeBajaDeporte(deporte,email);
		if (realizado) {
			resp.setStatus(HttpServletResponse.SC_OK);
			response = "El usuario se ha dado de baja correctamente del deporte";
		}
		else {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = "El usuario no se ha podido dar de baja del deporte";
		}
		setResponse(response, resp);
	}


	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String response = null;
		List<Deporte> deportes = null;
		String email = req.getParameter("email");
		
		if (email == null) {
			deportes = repo.listarDeportes();			
			if (deportes.isEmpty()) {
				response = "Error: No encuentra deportes en base de datos";
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				System.out.println("Error: No encuentra deportes en base de datos");
			}
			else {
				response= gson.toJson(deportes);				
				System.out.println("json ");
				System.out.println("si los deportes");
				System.out.println(response);
				resp.setStatus(HttpServletResponse.SC_OK);
			}
		}
		else {
			deportes = repo.listarDeportesUsuario(email);
			if (deportes.isEmpty()) {
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response = "El usuario no ha suscrito deportes";
			}
			else {
				resp.setStatus(HttpServletResponse.SC_OK);				
				response= gson.toJson(deportes);				
				System.out.println("si los Deportes usuario con json");
				System.out.println(response);
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
		try {			
			PrintWriter out = resp.getWriter();
			out.print(response);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}