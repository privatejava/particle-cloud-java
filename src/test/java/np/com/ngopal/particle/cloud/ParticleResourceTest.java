package np.com.ngopal.particle.cloud;

import np.com.ngopal.particle.cloud.api.Particle;
import np.com.ngopal.particle.cloud.api.exception.APIException;
import org.junit.Test;

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
/**
 *
 * @author Narayan <me@ngopal.com.np>
 */
public class ParticleResourceTest {

    public static Particle client() {
        return Particle.client("marcopolo-web-x-5662", "3c9ae65f71a011505dd9d746e5b9a725d34b717b");
    }

    public static Particle basic() {
        return Particle.basic("brian.quinn@cbts.net", "changemeCBTS!");
    }

    @Test
    public void accessToken() throws APIException {
        basic().api().auth().listAccessToken();
    }

}
