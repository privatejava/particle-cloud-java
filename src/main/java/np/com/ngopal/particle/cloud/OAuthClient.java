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
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author NGM
 */
@Getter
@AllArgsConstructor
public class OAuthClient extends AuthClient {

    private final String name;

    private OAuthClientType type;

    private String id;

    private final String secret;

    public OAuthClient(String name, String secret) {
        this.name = name;
        this.secret = secret;
    }

    @Override
    public String getUser() {
        return name;
    }

}
