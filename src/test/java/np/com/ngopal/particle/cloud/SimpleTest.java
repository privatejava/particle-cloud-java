package np.com.ngopal.particle.cloud;


/*
 * Copyright (C) 2016 Narayan G. Maharjan <me@ngopal.com.np>
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
import static java.awt.SystemColor.info;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import np.com.ngopal.particle.cloud.api.API;
import np.com.ngopal.particle.cloud.api.Particle;
import np.com.ngopal.particle.cloud.api.exception.APIException;

/**
 *
 * @author NGM
 */
@Slf4j
public class SimpleTest {

    public static void main(String[] args) {
     
        
        
        Customer customer = new Customer();
        customer.setEmail("luquexa@yahoo.com");
        customer.setPassword("testpass");
        try {
            System.out.println("Hello");
            API api = Particle.client("marcopolo-web-x-56623", "3c9ae65f71a011505dd9d746e5b9a725d34b717e").api();
            
//            api.auth().generateAccessToken();
//            Customer c = api.customers().createCustomer("marcopolo-v100", customer);
//            log.info("Customer: {}", c);

            AccessToken token = api.auth().generateAccessToken(); 
//            api.events().productPublish("marcopolo-v100","DEV440031001451343334363036", "test", false, 60);
//            api.events().publish("DEV440031001451343334363036", "test", Boolean.FALSE, 60);
//          
            System.out.println(token.getAccessToken());
            
//            PaginatedSimCard usage = api.simCards().getSims("marcopolo-v100", "", "", 0, 10); 
//            for (SIMCard sim : usage.getSims()) {
//                log.info("{}", sim);
//            }
            
            
            
//            api.devices().

//
//            DeviceClaim claim = api.products().generateDeviceClaimCode("marcopolo-v100", "privatejava@gmail2.com");
//            log.debug("Claim: {}", claim);
//            claim = api.devices().createClaim("8997702152290790939", "privatejava@gmail2.com");
//
//            log.debug("Claim: {}", claim);
////            API api = Particle.basic("me@ngopal.com.np", "dffe").api();
////            List<Device> list = api.devices().listDevices();
//            List<Customer> customers = Particle.client("marcopolo-web-x-5662", "3c9ae65f71a011505dd9d746e5b9a725d34b717b").api().customers().listCustomer("marcopolo-v100");
//            log.debug("List {}", customers);
////            System.out.println(customerRet);
        } catch (APIException ex) {
            Logger.getLogger(SimpleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
