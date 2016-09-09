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

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author NGM
 */
public class CloudAPI {

    public static void main(String[] args) throws UnirestException, IOException {
        HttpResponse<JsonNode> jsonResponse = Unirest.post("https://api.particle.io/oauth/token")
                .basicAuth("marcopolo-web-x-5662", "3c9ae65f71a011505dd9d746e5b9a725d34b717b")
                .header("accept", "application/json")
                .field("grant_type", "password")
                .field("username", "brian.quinn@cbts.net")
                .field("password", "changemeCBTS!")
                .asJson();
        System.out.println(IOUtils.toString(jsonResponse.getRawBody()));
    }

}
