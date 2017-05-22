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

import com.google.gson.annotations.SerializedName;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author NGM
 */
@Getter
@Setter
@ToString
public class SIMCard {

    @SerializedName("_id")
    private String iccid;

    @SerializedName("activations_count")
    private Integer activationCount;

    @SerializedName("base_country_code")
    private String baseCountryCode;

    @SerializedName("base_monthly_rate")
    private Float baseMonthlyRate;

    @SerializedName("deactivations_count")
    private Integer deactivationCount;

    @SerializedName("first_activated_on")
    private Date firstActivatedOn;

    @SerializedName("mb_limit")
    private Float mbLimit;

    @SerializedName("user_id")
    private Float email;

    @SerializedName("overage_monthly_rate")
    private Integer overageMonthlyRate;

    @SerializedName("last_device_id")
    private String lastDeviceId;

    @SerializedName("status")
    private String status;
}
