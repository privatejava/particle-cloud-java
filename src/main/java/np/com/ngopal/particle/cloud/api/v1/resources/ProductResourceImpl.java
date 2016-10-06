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
package np.com.ngopal.particle.cloud.api.v1.resources;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import np.com.ngopal.particle.cloud.DeviceClaim;
import np.com.ngopal.particle.cloud.api.API;
import np.com.ngopal.particle.cloud.api.APIMethodType;
import np.com.ngopal.particle.cloud.api.exception.APIException;
import np.com.ngopal.particle.cloud.api.resources.ProductResource;
import org.json.JSONObject;

/**
 *
 * @author Narayan <me@ngopal.com.np>
 */
@Getter
@Slf4j
public class ProductResourceImpl extends ProductResource {

    private String baseURIPattern = "/products";

    public ProductResourceImpl(API api) {
        super(api);
    }

    @Override
    public DeviceClaim generateDeviceClaimCode(String productId, String customerEmail)
            throws APIException {

        log.debug("API:{}", getApi());
        Map<String, String> headers = getApi().getAccessTokenAuthHeaders(customerEmail);
        try {
            String url = String.format("%s%s/%s/%s", getApi().getRestUrl(), baseURIPattern, productId, "device_claims");
            HttpResponse<JsonNode> response = getRestClient(APIMethodType.POST, url, headers).asJson();
            if (response.getStatus() == 200) {
                return gson.fromJson(response.getBody().toString(), DeviceClaim.class);
            } else {
                api.handleException(response.getBody().getObject());
            }
        } catch (UnirestException ex) {
            log.error("UnirestException: {}", ex.getMessage(), ex);
            throw new APIException(ex);
        }
        return null;
    }

//    private HttpRequest getProductRestClient(APIMethodType type,String url)
//            throws APIException {
//
//        getRestClient();
//
//        Map<String, String> headers = getApi().getAccessTokenAuthHeaders();
//        log.debug("Headers: {}", headers);
//
//
//        String url = urlapi.getRestUrl() + getBaseURIPattern().replace("/:deviceId", deviceId == null ? "" : "/" + deviceId).replace(":name", name == null ? "" : "/" + name);
//        log.debug("URL : {}", url);
//        switch (type) {
//            case GET:
//                return Unirest.get(url).headers(headers);
//            case POST:
//                return Unirest.post(url).headers(headers);
//            case DELETE:
//                return Unirest.delete(url).headers(headers);
//        }
//
//        return null;
//    }
}
