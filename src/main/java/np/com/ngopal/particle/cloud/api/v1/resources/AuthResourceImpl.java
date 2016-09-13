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
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Duration;
import lombok.extern.slf4j.Slf4j;
import np.com.ngopal.particle.cloud.AccessToken;
import np.com.ngopal.particle.cloud.OAuthClient;
import np.com.ngopal.particle.cloud.OAuthClientType;
import np.com.ngopal.particle.cloud.api.API;
import np.com.ngopal.particle.cloud.api.APIMethodType;
import np.com.ngopal.particle.cloud.api.AbstractAPI;
import np.com.ngopal.particle.cloud.api.exception.APIException;
import np.com.ngopal.particle.cloud.api.resources.AuthResource;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Narayan <me@ngopal.com.np>
 */
@Slf4j
public class AuthResourceImpl extends AuthResource {

    private String baseURIPattern = "/access_tokens";

    public AuthResourceImpl(API api) {
        super(api);
    }

    @Override
    public AccessToken generateAccessToken(String grantType) throws APIException {
        AbstractAPI api_ = (AbstractAPI) api;
        try {

            HttpResponse<JsonNode> response = ((HttpRequestWithBody) getAuthCreateRestClient(APIMethodType.POST, "/oauth/token"))
                    .field("username", api_.getClient().getEmail())
                    .field("password", api.getClient().getPassword())
                    .field("grant_type", grantType).asJson();

            if (response.getStatus() == 200) {
                JSONObject object = response.getBody().getObject();
                log.debug("{}", object);
                api_.getClient().setAccessToken(object.getString("access_token"));
                Long date = System.currentTimeMillis() + (object.getLong("expires_in") * 1000);
                api_.getClient().setExpiresIn(new Date(date));
                api_.getClient().setRefreshToken(object.getString("refresh_token"));
                api_.getClient().setTokenType(object.getString("token_type"));
            } else {
                api.handleException(response.getBody().getObject());
            }
        } catch (UnirestException ex) {
            log.error("{}", ex);
            throw new APIException(ex);
        }
        return api_.getClient();
    }

    @Override
    public List<AccessToken> listAccessToken() throws APIException {
        List<AccessToken> tokens = new ArrayList<>();
        try {
            HttpResponse<JsonNode> response = getAuthCreateRestClient(APIMethodType.GET).asJson();
            if (response.getStatus() == 200) {
                JSONArray array = response.getBody().getArray();
                if (array.length() > 0) {

                    array.iterator().forEachRemaining(obj -> {
                        JSONObject object = (JSONObject) obj;
                        String dateStr = object.isNull("expires_at") ? null : object.getString("expires_at");
                        String client = object.isNull("client") ? null : object.getString("client");
                        SimpleDateFormat format = new SimpleDateFormat(API.DATE_FORMAT);
                        OAuthClient token = new OAuthClient(client, "");
                        token.setAccessToken(object.getString("token"));
                        try {
                            if (dateStr != null) {
                                Date birthDate = format.parse(dateStr);
                                token.setExpiresIn(birthDate);
                            }

                        } catch (ParseException ex) {
                            log.error("Date parse Exception", ex);
                        }
                        tokens.add(token);

                    });

                }

            } else {
                api.handleException(response.getBody().getObject());
            }

        } catch (UnirestException ex) {
            log.debug("{}", ex);
            throw new APIException(ex);
        }

        return tokens;
    }

    @Override
    public String getBaseURIPattern() {
        return baseURIPattern;
    }

    private HttpRequest getAuthCreateRestClient(APIMethodType type, String uri) {

        HttpRequestWithBody req = null;
        Map<String, String> headers = getApi().getAuthHeaders();
        String url = api.getNonVersionedRestUrl() + (uri == null ? getBaseURIPattern() : uri);
        log.debug("Headers: {}", headers);
        log.debug("URL : {}", url);
        switch (type) {
            case GET:
                return Unirest.get(url).headers(headers);
            case POST:
                return Unirest.post(url).headers(headers);
            case DELETE:
                return Unirest.delete(url).headers(headers);
        }

        return null;
    }

    private HttpRequest getAuthCreateRestClient(APIMethodType type) {
        return getAuthCreateRestClient(type, null);
    }

}
