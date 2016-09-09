/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.ngopal.particle.cloud.api.resources;

import java.util.List;
import lombok.Getter;
import np.com.ngopal.particle.cloud.Customer;
import np.com.ngopal.particle.cloud.api.API;
import np.com.ngopal.particle.cloud.api.exception.APIException;

/**
 *
 * @author NGM
 */
@Getter
public abstract class CustomerResource extends APIResource {

    public CustomerResource(API api) {
        super(api);
    }

    public abstract Customer createCustomer(String productOrSlug, Customer customer)
            throws APIException;

    public abstract List<Customer> listCustomer() throws APIException;

}
