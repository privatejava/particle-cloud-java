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
