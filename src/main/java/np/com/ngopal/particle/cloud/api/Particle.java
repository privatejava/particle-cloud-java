/*
 * Copyright (C) 2016 Narayan G. Maharjan <me@ngopal.com.np>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package np.com.ngopal.particle.cloud.api;

import np.com.ngopal.particle.cloud.AuthUser;
import np.com.ngopal.particle.cloud.BasicAuth;
import np.com.ngopal.particle.cloud.OAuthClient;
import np.com.ngopal.particle.cloud.api.resources.ProductResource;
import np.com.ngopal.particle.cloud.api.v1.APIv1;
import np.com.ngopal.particle.cloud.api.v1.resources.AuthResourceImpl;
import np.com.ngopal.particle.cloud.api.v1.resources.CustomerResourceImpl;
import np.com.ngopal.particle.cloud.api.v1.resources.DeviceResourceImpl;
import np.com.ngopal.particle.cloud.api.v1.resources.ProductResourceImpl;

/**
 *
 * @author NGM
 */
public final class Particle {

    private boolean isToken = false;

    private AuthUser client;

    private String token;

    private API api;

    private Particle(String user, String secret, boolean isClient) {
        this.isToken = false;
        if (isClient) {
            client = new OAuthClient(user, secret);
        } else {
            client = new BasicAuth(user, secret);
        }

    }

    private Particle(String token) {
        this.isToken = true;
        this.token = token;

    }

    public static Particle client(String user, String pass) {
        Particle particle = new Particle(user, pass, true);
        return particle;
    }

    public static Particle basic(String user, String pass) {
        Particle particle = new Particle(user, pass, false);
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
            apiV1.setDeviceResource(new DeviceResourceImpl(apiV1));
            apiV1.setAuthResource(new AuthResourceImpl(apiV1));
            apiV1.setProductResource(new ProductResourceImpl(apiV1));
            api = apiV1;
        }
        return api;
    }

}
