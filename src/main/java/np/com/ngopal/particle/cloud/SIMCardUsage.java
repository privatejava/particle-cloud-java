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
import java.util.List;
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
public class SIMCardUsage {

    private Float iccid;

    @SerializedName("total_mb_used")
    private Float totalMBUsed;

    @SerializedName("total_active_sim_cards")
    private Float totalActiveSimCards;

    @SerializedName("total_cost")
    private Float totalCost;

    @SerializedName(value = "daily_usage", alternate = {"usage_by_day"})
    private List<Usage> dailyUsage;

    @Getter
    @Setter
    @ToString
    final class Usage {

        @SerializedName("date")
        private Date date;

        @SerializedName("mb_used")
        private Float mbUsed;

        @SerializedName("mb_used_cumulative")
        private Float mbUsedCumulative;
        
    }
}
