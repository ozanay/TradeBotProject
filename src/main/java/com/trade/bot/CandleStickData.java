package com.trade.bot;

import java.util.Date;

/**
 * @author Ozan Ay
 */
public interface CandleStickData {
    Date getOpenTime();
    double getOpenPrice();
    double getHighPrice();
    double getLowPrice();
    double getClosePrice();
    String getVolume();
    Date getCloseTime();
    String getQuoteAssetVolume();
    Long getNumberOfTrades();
    String getTakerBuyBaseAssetVolume();
    String getTakerBuyQuoteAssetVolume();
}
