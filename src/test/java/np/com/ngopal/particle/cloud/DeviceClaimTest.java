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

package np.com.ngopal.particle.cloud;

import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import np.com.ngopal.particle.cloud.api.Particle;
import np.com.ngopal.particle.cloud.api.exception.APIException;
import org.junit.Test;

/**
 *
 * @author NGM
 */
@Slf4j
public class DeviceClaimTest {

    @Test
    public void main() {
        try {
            Particle particle = Particle.client("<client-id>", "<client-secret>");
            SIMCardUsage usage = particle.api().simCards().getDataUsageForProductFleet("<product-id>");
        } catch (APIException ex) {
            Logger.getLogger(DeviceClaimTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
