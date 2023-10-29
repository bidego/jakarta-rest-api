package org.eclipse.rest.api.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.rest.api.dao.DaoController;
import org.eclipse.rest.api.dao.Employee;
import org.eclipse.rest.api.models.User;
import org.eclipse.rest.api.services.UserService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("user")
public class UserResource {

	@Inject
	Logger logger;

	@Inject
	private UserService userService;

	@Inject
	DaoController dao;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Employee> get(@QueryParam("name") String name) {
		logger.info("GET user");
		List<Employee> response = this.userService.get(name);
		logger.info(String.format("Ctrl Out: %s", UserService.counter));
		return response;
	}

	@POST
	@Path("{name}")
	@Produces({ MediaType.APPLICATION_JSON })
	public User save(@PathParam("name") String name) {
		logger.info("POST /user/{name}");

		if ((name == null) || name.trim().isEmpty())  {
			name = "world";
		}
		System.out.println("Create Emplyee");

		Employee employee = new Employee();
		employee.setFirstName(name);
		dao.save(employee);
		return new User(name);
	}
	@PATCH
	@Path("{name}")
	@Produces({ MediaType.APPLICATION_JSON })
	public User save(@PathParam("name") String name, @QueryParam("mensaje") String mensaje) {
		logger.info("PATCH user");
		if ((name == null) || name.trim().isEmpty())  {
			name = "world: " + mensaje;
		}

		return new User(name, mensaje);
	}
}