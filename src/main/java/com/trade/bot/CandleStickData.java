package com.trade.bot;

/**
 * @author Ozan Ay
 */
public interface CandleStickData {
    TradeData getOpenTradeData();
    TradeData getCloseTradeData();
    double getHighPrice();
    double getLowPrice();
    String getVolume();
    String getQuoteAssetVolume();
    Long getNumberOfTrades();
    String getTakerBuyBaseAssetVolume();
    String getTakerBuyQuoteAssetVolume();
}
