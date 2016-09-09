/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    private HttpRequestWithBody getCustomerCreateRestClient(String productIdOrSlug) {
        log.debug("URL : {}" + api.getRestUrl() + getBaseURIPattern().replace(":productIdOrSlug", productIdOrSlug));
        log.debug("Headers: {}" + getApi().getAuthHeaders());

        return Unirest.post(api.getRestUrl() + getBaseURIPattern().replace(":productIdOrSlug", productIdOrSlug))
                .headers(getApi().getAuthHeaders());
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
