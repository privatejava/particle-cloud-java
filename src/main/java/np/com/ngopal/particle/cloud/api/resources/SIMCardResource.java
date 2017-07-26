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

package np.com.ngopal.particle.cloud.api.resources;

import java.util.List;
import lombok.Getter;
import np.com.ngopal.particle.cloud.PaginatedSimCard;
import np.com.ngopal.particle.cloud.SIMCardUsage;
import np.com.ngopal.particle.cloud.api.API;
import np.com.ngopal.particle.cloud.api.exception.APIException;

/**
 *
 * @author NGM
 */
@Getter
public abstract class SIMCardResource extends APIResource {

    public SIMCardResource(API api) {
        super(api);
    }

    public abstract SIMCardUsage getDataUsageForProductFleet(String productId)
            throws APIException;
    
    public abstract SIMCardUsage getSIMDataUsage(String productId,String iccid)
            throws APIException;
    
    public abstract PaginatedSimCard getSims(String productId,String deviceId, String deviceName, int page, int limit )
            throws APIException;
    
    public abstract boolean importAndActivate(String productSlug,List<String> sims) throws APIException;
    
    public abstract boolean deactivate(String productSlug, String iccid) throws APIException;
    
    public abstract boolean reactive( String iccid) throws APIException;
    
    public abstract boolean setDataLimit(int mb, String iccid,String productSlug) throws APIException;
    
}

