/*-
 * --------------------------------------------------------------------
 * SimpleTest.java - particle-cloud - 2016
 * org.codehaus.mojo-license-maven-plugin-1.9
 * http://ngopal.com.np
 * %%
 * Copyright (C) 2016 Ngopal
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * --------------------------------------------------------------------
 */
/*-
 * ========================LICENSE_START=================================
 * SimpleTest.java - particle-cloud - 2,016
 * org.codehaus.mojo-license-maven-plugin-1.9
 * $Id$
 * $HeadURL$
 * %%
 * Copyright (C) 2016 Ngopal
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * =========================LICENSE_END==================================
 */
/*-
 * #%L
 * SimpleTest.java - particle-cloud - 2,016
 * org.codehaus.mojo-license-maven-plugin-1.9
 * $Id$
 * $HeadURL$
 * %%
 * Copyright (C) 2016 Ngopal
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import np.com.ngopal.particle.cloud.Customer;
import np.com.ngopal.particle.cloud.api.Particle;
import np.com.ngopal.particle.cloud.api.exception.APIException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author NGM
 */
public class SimpleTest {

    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.setEmail("privatejava@gmail2.com");
        customer.setPassword("pass");
        try {
            Customer customerRet = Particle.client("marcopolo-web-5662", "3c9ae65f71a011505dd9d746e5b9a725d34b717b").api().customers().createCustomer("marcopolo-v100", customer);
            System.out.println(customerRet);
        } catch (APIException ex) {
            Logger.getLogger(SimpleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
