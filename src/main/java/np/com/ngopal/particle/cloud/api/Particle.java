/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.ngopal.particle.cloud.api;

import np.com.ngopal.particle.cloud.AuthClient;
import np.com.ngopal.particle.cloud.BasicAuthClient;
import np.com.ngopal.particle.cloud.OAuthClient;
import np.com.ngopal.particle.cloud.api.v1.APIv1;
import np.com.ngopal.particle.cloud.api.v1.resources.CustomerResourceImpl;

/**
 *
 * @author NGM
 */
public final class Particle {

    private boolean isToken = false;

    private AuthClient client;

    private String token;

    private API api;

    private Particle(String user, String secret) {
        this.isToken = false;

        client = new BasicAuthClient(user, secret);

    }

    private Particle(String token) {
        this.isToken = true;
        this.token = token;

    }

    public static Particle client(String user, String pass) {
        Particle particle = new Particle(user, pass);
        return particle;
    }

    public static Particle basic(String user, String pass) {
        Particle particle = new Particle(user, pass);
        return particle;
    }

    public static Particle accessToken(String token) {
        Particle particle = new Particle(token);
        return particle;
    }

    public API api() {
        if (api == null) {
            APIv1 apiV1 = null;
            apiV1 = client == null ? new APIv1(token) : new APIv1(client);
            apiV1.setCustomerResource(new CustomerResourceImpl(apiV1));
            api = apiV1;
        }
        return api;
    }

}
