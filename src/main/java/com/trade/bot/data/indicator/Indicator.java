package com.trade.bot.data.indicator;

import com.trade.bot.CommercialFlag;
import com.trade.bot.TradeData;

import java.util.List;

/**
 * @author Ozan Ay
 */
public interface Indicator {
    CommercialFlag apply(List<TradeData> tradeDataList);
}
