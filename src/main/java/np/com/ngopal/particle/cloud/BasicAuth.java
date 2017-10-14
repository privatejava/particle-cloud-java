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
package np.com.ngopal.particle.cloud;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author Narayan <me@ngopal.com.np>
 */
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
public class BasicAuth extends AuthUser {

    private final String id;

    private final String secret;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getSecret() {
        return secret;
    }

    @Override
    public String getGrantType() {
        return "password";
    }

}
