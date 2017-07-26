/*
 * Copyright (C) 2017 Narayan G. Maharjan <me@ngopal.com.np>
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

package np.com.ngopal.particle.cloud.api.v1.resources;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import np.com.ngopal.particle.cloud.SIMCardUsage;
import np.com.ngopal.particle.cloud.api.API;
import np.com.ngopal.particle.cloud.api.APIMethodType;
import np.com.ngopal.particle.cloud.api.exception.APIException;
import np.com.ngopal.particle.cloud.api.resources.EventResource;

/**
 *
 * @author NGM
 */
@Slf4j
@Getter
public class EventResourceImpl extends EventResource {

    private String baseURIPattern = "/events";

    public EventResourceImpl(API api) {
        super(api);
    }

    @Override
    public boolean publish(String name, String data, Boolean privateMode, int ttl) throws APIException {
        String url = String.format("%s/devices%s", getApi().getRestUrl(), baseURIPattern);
        return publishEvent(url, name, data, privateMode, ttl);
    }

    @Override
    public boolean productPublish(String productId, String name, String data, Boolean privateMode, int ttl) throws APIException {
        String url = String.format("%s/products/%s%s", getApi().getRestUrl(), productId, baseURIPattern);
        return publishEvent(url, name, data, privateMode, ttl);
    }

    private boolean publishEvent(String url, String name, String data, Boolean privateMode, int ttl) throws APIException {
        log.debug("API:{}", getApi());

        Map<String, String> headers = getApi().getAccessTokenAuthHeaders();
        try {
            log.info("Req: {}", url);
            HttpRequestWithBody request = ((HttpRequestWithBody) getRestClient(APIMethodType.POST, url, headers));
            Map<String, Object> requestData = new HashMap<>();
            requestData.put("name", name);
            requestData.put("data", data);
            requestData.put("ttl", ttl);
            if (privateMode != null) {
                requestData.put("private", privateMode);
            }
            HttpResponse<JsonNode> response = request.fields(requestData).asJson(); 

            if (response.getStatus() == 200) {
                log.debug("Body: {}", response.getBody().toString());
                return true;
            } else {
                api.handleException(response.getBody().getObject());
            }
        } catch (UnirestException ex) {
            log.error("UnirestException: {}", ex.getMessage(), ex);
            throw new APIException(ex);
        }
        return false;
    }

}
