package com.sap.ase;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Facebook implements SocialMedia {

	@Override
	public Response post(int potSize) {
		String message = "I've won " + potSize + " chips in ASE POKER!";
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://" + "facebook.com/api/wall");
		return webTarget.request().post(Entity.entity(message, MediaType.TEXT_PLAIN));
	}
}
