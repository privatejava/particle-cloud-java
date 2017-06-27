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
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import np.com.ngopal.particle.cloud.PaginatedSimCard;
import np.com.ngopal.particle.cloud.SIMCardUsage;
import np.com.ngopal.particle.cloud.api.API;
import np.com.ngopal.particle.cloud.api.APIMethodType;
import np.com.ngopal.particle.cloud.api.exception.APIException;
import np.com.ngopal.particle.cloud.api.resources.SIMCardResource;
import org.json.JSONObject;

/**
 *
 * @author NGM
 */
@Slf4j
@Getter
public class SIMCardResourceImpl extends SIMCardResource {

    private String baseURIPattern = "/products";

    public SIMCardResourceImpl(API api) {
        super(api);
    }

    @Override
    public SIMCardUsage getDataUsageForProductFleet(String productId) throws APIException {
        log.debug("API:{}", getApi());

        Map<String, String> headers = getApi().getAccessTokenAuthHeaders();
        try {
            String url = String.format("%s%s/%s/%s", getApi().getRestUrl(), baseURIPattern, productId, "sims/data_usage");
            HttpResponse<JsonNode> response = getRestClient(APIMethodType.GET, url, headers).asJson();
            if (response.getStatus() == 200) {
                log.debug("Body: {}", response.getBody().toString());
                return gson.fromJson(response.getBody().toString(), SIMCardUsage.class);
            } else {
                api.handleException(response.getBody().getObject());
            }
        } catch (UnirestException ex) {
            log.error("UnirestException: {}", ex.getMessage(), ex);
            throw new APIException(ex);
        }
        return null;
    }

    @Override
    public SIMCardUsage getSIMDataUsage(String productId, String iccid) throws APIException {

        log.debug("API:{}", getApi());

        Map<String, String> headers = getApi().getAccessTokenAuthHeaders();
        try {
            String url = String.format("%s%s/%s/sims/%s/data_usage", getApi().getRestUrl(), baseURIPattern, productId, iccid);
            HttpResponse<JsonNode> response = getRestClient(APIMethodType.GET, url, headers).asJson();
            if (response.getStatus() == 200) {
                log.debug("Body: {}", response.getBody().toString());
                SIMCardUsage usage = gson.fromJson(response.getBody().toString(), SIMCardUsage.class);
                log.debug("{}", usage);
                return usage;
            } else {
                api.handleException(response.getBody().getObject());
            }
        } catch (UnirestException ex) {
            log.error("UnirestException: {}", ex.getMessage(), ex);
            throw new APIException(ex);
        }
        return null;
    }

    @Override
    public PaginatedSimCard getSims(String productSlug, String deviceId, String deviceName, int page, int limit) throws APIException {
        log.debug("API:{}", getApi());
        Map<String, String> headers = getApi().getAccessTokenAuthHeaders();
        try {
            String url = String.format("%s%s/%s/%s", getApi().getRestUrl(), baseURIPattern, productSlug, "sims");
            log.debug("Lim: {}, page:{}", limit, page);
            HttpRequest request = getRestClient(APIMethodType.GET, url, headers);
            if (deviceId != null && !deviceId.isEmpty()) {
                request.queryString("deviceId", deviceId);
            }
            if (deviceName != null && !deviceName.isEmpty()) {
                request.queryString("deviceName", deviceId);
            }
            request.queryString("page", page)
                    .queryString("perPage", limit);
            log.debug("Request >> :: {}", request.getUrl());
            HttpResponse<JsonNode> response = request.asJson();

            if (response.getStatus() == 200) {
                log.debug("Body: {}", response.getBody().toString());
                return gson.fromJson(response.getBody().toString(), PaginatedSimCard.class);
            } else {
                api.handleException(response.getBody().getObject());
            }
        } catch (UnirestException ex) {
            log.error("UnirestException: {}", ex.getMessage(), ex);
            throw new APIException(ex);
        }
        return null;
    }

    @Override
    public boolean deactivate(String productSlug, String iccid) throws APIException {
        Map<String, String> headers = getApi().getAccessTokenAuthHeaders();
        try {
            String url = String.format("%s%s/%s/%s/%s", getApi().getRestUrl(), baseURIPattern, productSlug, "sims", iccid);
            HttpRequestWithBody request = ((HttpRequestWithBody) getRestClient(APIMethodType.PUT, url, headers));
            HttpResponse<JsonNode> response = request.field("action", "deactivate").asJson();
            if (response.getStatus() == 200) {
                return true;
            } else {
                api.handleException(response.getBody().getObject());
            }
        } catch (Exception ex) {
            log.error("ImportAndActivateException: {}", ex.getMessage(), ex);
            throw new APIException(ex);
        }
        
        return false;
    }
    
    @Override
    public boolean importAndActivate(String productSlug, List<String> sims) throws APIException {
        
        Map<String, String> headers = getApi().getAccessTokenAuthHeaders();
        try {
            String url = String.format("%s%s/%s/%s", getApi().getRestUrl(), baseURIPattern, productSlug, "sims");
            HttpRequestWithBody request = ((HttpRequestWithBody) getRestClient(APIMethodType.POST, url, headers));
            request.header("Content-Type", "application/json");
            JSONObject object = new JSONObject();
            object.put("country", "US");
            object.put("sims", sims);
            object.put("action", "reactivate");
            request.body(object);
            HttpResponse<JsonNode> response = request.asJson();
            if (response.getStatus() == 200) {
                log.debug("Body: {}", response.getBody().toString());
                return response.getBody().getObject().has("ok") && response.getBody().getObject().getBoolean("ok"); 
            } else {
                api.handleException(response.getBody().getObject());
            }
        } catch (Exception ex) {
            log.error("ImportAndActivateException: {}", ex.getMessage(), ex);
            throw new APIException(ex);
        }
        return false;
    }

    @Override
    public boolean setDataLimit(int mbLimit, String iccid, String productSlug) throws APIException {
         Map<String, String> headers = getApi().getAccessTokenAuthHeaders();
        try {
            String url = String.format("%s/%s/%s", getApi().getRestUrl(), "sims", iccid);
            HttpRequestWithBody request = ((HttpRequestWithBody) getRestClient(APIMethodType.PUT, url, headers));
            request.header("Content-Type", "application/json");
            JSONObject object = new JSONObject();
            object.put("mb_limit",mbLimit);
            object.put("productIdOrSlug", productSlug);
            request.body(object);
            HttpResponse<JsonNode> response = request.asJson();
            if (response.getStatus() == 200) {
                return true; 
            } else {
                api.handleException(response.getBody().getObject());
            }
        } catch (Exception ex) {
            log.error("SetDataLimit: {}", ex.getMessage(), ex);
            throw new APIException(ex);
        }
        return false;
    }

}
