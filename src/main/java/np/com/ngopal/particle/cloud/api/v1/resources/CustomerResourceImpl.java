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
import com.mashape.unirest.request.HttpRequestWithBody;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import np.com.ngopal.particle.cloud.Customer;
import np.com.ngopal.particle.cloud.api.API;
import np.com.ngopal.particle.cloud.api.AbstractAPI;
import np.com.ngopal.particle.cloud.api.exception.APIException;
import np.com.ngopal.particle.cloud.api.resources.CustomerResource;
import org.json.JSONObject;

/**
 *
 * @author NGM
 */
@Getter
@Slf4j
public class CustomerResourceImpl extends CustomerResource {

    private String baseURIPattern = "/products/:productIdOrSlug/customers";

    public CustomerResourceImpl(API api) {
        super(api);
    }

    private HttpRequestWithBody getCustomerCreateRestClient(String productIdOrSlug)
            throws APIException {
        log.debug("URL : {}" + api.getRestUrl() + getBaseURIPattern().replace(":productIdOrSlug", productIdOrSlug));
        log.debug("Headers: {}" + getApi().getAccessTokenAuthHeaders());

        return Unirest.post(api.getRestUrl() + getBaseURIPattern().replace(":productIdOrSlug", productIdOrSlug))
                .headers(getApi().getAccessTokenAuthHeaders());
    }

    @Override
    public Customer createCustomer(String productIdOrSlug, Customer customer)
            throws APIException {
        try {

            HttpResponse<JsonNode> response = getCustomerCreateRestClient(productIdOrSlug)
                    .field("email", customer.getEmail())
                    .field("password", customer.getPassword())
                    .asJson();
            if (response.getStatus() == 201) {
                JSONObject object = response.getBody().getObject();
                customer.setTokenType(object.getString("token_type"));
                customer.setExpiresIn(object.getLong("expires_in"));
                customer.setAccessToken(object.getString("access_token"));
                customer.setRefreshToken(object.getString("refresh_token"));
            } else {
                api.handleException(response.getBody().getObject());
            }
        } catch (UnirestException ex) {
            log.debug("{}", ex);
            throw new APIException(ex);
        }
        return customer;
    }

    @Override
    public List<Customer> listCustomer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
