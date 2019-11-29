package com.sap.ase;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class SocialMedia {

	public boolean postMessageToFacebook(Player player, int potSize){
		// post message to my facebook wall
		String message = "I've won " + potSize + " chips in ASE POKER!";
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://sapfacebook9999.com/api/wall");
		webTarget.request().post(Entity.entity(message, MediaType.TEXT_PLAIN));
		return true; //Need to refactor to know whether publish is success or not
	}

}