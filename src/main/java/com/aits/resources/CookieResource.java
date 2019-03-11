package com.aits.resources;

import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/cookie")
public class CookieResource {

	/**
	 * This method is used to write Cookies in Response
	 * @return Response
	 */
	@GET
	@Path("/write")
	public Response writeCookies() {
		NewCookie cookie1 = new NewCookie("uname", "ashok");
		NewCookie cookie2 = new NewCookie("pwd", "ashok@123");

		ResponseBuilder rb = Response.ok("Cookies Added Successfully");

		rb.cookie(cookie1, cookie2);

		return rb.build();
	}

	/**
	 * This method is used to inject cookie value to method using key name
	 * @param uname
	 * @return Response
	 */
	@GET
	@Path("/readByKey")
	public Response getCookieByName(@CookieParam("uname") String uname) {
		String msg = "uname cookie value is : " + uname;
		return Response.ok(msg).build();
	}

	/**
	 * This method is used to read all cookies
	 * @param httpHeaders
	 * @return Response
	 */
	@GET
	@Path("/readAll")
	public Response getallCookies(@Context HttpHeaders httpHeaders) {
		Map<String, Cookie> cookies = httpHeaders.getCookies();
		
	String responseMsg  =  cookies.entrySet()
		 						  .stream()
		 						  .map(e -> e.getKey()+" = "+e.getValue().getValue())
		 						  .collect(Collectors.joining("<br/>"));
		
		return Response.ok(responseMsg).build();
	}
}
