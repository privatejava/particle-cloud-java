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
package np.com.ngopal.particle.cloud.api.v1;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.*;
import np.com.ngopal.particle.cloud.AuthClient;
import np.com.ngopal.particle.cloud.OAuthClient;
import np.com.ngopal.particle.cloud.api.AbstractAPI;
import np.com.ngopal.particle.cloud.api.resources.AuthResource;
import np.com.ngopal.particle.cloud.api.resources.CustomerResource;
import np.com.ngopal.particle.cloud.api.resources.DeviceResource;

/**
 *
 * @author NGM
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public final class APIv1 extends AbstractAPI {

    private String version = "v1";

    @Getter(AccessLevel.NONE)
    @Setter
    private CustomerResource customerResource;

    @Getter(AccessLevel.NONE)
    @Setter
    private DeviceResource deviceResource;

    @Getter(AccessLevel.NONE)
    @Setter
    private AuthResource authResource;

    public APIv1(AuthClient client) {
        this.client = client;
    }

    public APIv1(String token) {
        this.client = new OAuthClient("", "");
        this.client.setAccessToken(token);
    }

    public String getRestUrl() {
        return String.format("%s://%s/%s", getSchema(), getHost(), getVersion());
    }

    public String getNonVersionedRestUrl() {
        return String.format("%s://%s", getSchema(), getHost());
    }

    public void d(String[] args) throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = Unirest.post("https://api.particle.io/oauth/token")
                .basicAuth("marcopolo-web-x-5662", "3c9ae65f71a011505dd9d746e5b9a725d34b717b")
                .header("accept", "application/json")
                .field("grant_type", "password")
                .field("username", "brian.quinn@cbts.net")
                .field("password", "changemeCBTS!")
                .asJson();
    }

    @Override
    public CustomerResource customers() {
        return this.customerResource;
    }

    @Override
    public DeviceResource devices() {
        return this.deviceResource;
    }

    @Override
    public AuthResource auth() {
        return this.authResource;
    }

}
