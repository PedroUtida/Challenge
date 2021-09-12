package br.com.fiap.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.dao.CadastroDAO;
import br.com.fiap.model.Cadastro;

@Path("/setups")
public class CadastroEndpoint {
	
	private CadastroDAO dao = new CadastroDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cadastro> index(){
		return dao.getAll();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Cadastro cadastro) {
		if(cadastro == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(cadastro).build();
		}
		dao.save(cadastro);
		return Response.status(Response.Status.CREATED).entity(cadastro).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response show(@PathParam("id") Long id) {
		Cadastro cadastro;
		try {
			cadastro = dao.findById(id);
			if(cadastro == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
			return Response.status(Response.Status.OK).entity(cadastro).build();
		} catch(Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response update(@PathParam("id") Long id, Cadastro cadastro) {
		cadastro.setId(id);
		dao.update(cadastro);
		return Response.status(Response.Status.OK).entity(cadastro).build();

	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response delete(@PathParam("id") Long id, Cadastro cadastro) {
		if (id == null) {
			return Response.status(Response.Status.BAD_REQUEST).build(); 
		}
		if (cadastro == null) {
			return Response.status(Response.Status.BAD_REQUEST).build(); 
		}
		if (dao.findById(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build(); 
		}
		
		cadastro.setId(id);
		
		try {
			dao.delete(cadastro);
			return Response.status(Response.Status.OK).entity(cadastro).build(); 
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	
	 
}
}
	

