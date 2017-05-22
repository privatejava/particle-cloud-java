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
package np.com.ngopal.particle.cloud.api.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequest;
import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import np.com.ngopal.particle.cloud.api.API;
import np.com.ngopal.particle.cloud.api.APIMethodType;
import np.com.ngopal.particle.cloud.api.exception.APIException;

/**
 *
 * @author NGM
 */
@Slf4j
@Getter
public abstract class APIResource {

    protected Gson gson;

    protected API api;

    public APIResource(API api) {
        this.api = api;
        gson = new GsonBuilder().setDateFormat(API.DATE_FORMAT).create();
    }

    public abstract String getBaseURIPattern();

    protected HttpRequest getRestClient(APIMethodType type, String url, Map<String, String> headers)
            throws APIException {
        log.debug("URL : {}", url);
        log.debug("Headers: {}", headers);

        switch (type) {
            case GET:
                return Unirest.get(url).headers(headers);
            case POST:
                return Unirest.post(url).headers(headers);
            case PUT:
                return Unirest.put(url).headers(headers);
            case DELETE:
                return Unirest.delete(url).headers(headers);
        }

        return null;
    }

}
