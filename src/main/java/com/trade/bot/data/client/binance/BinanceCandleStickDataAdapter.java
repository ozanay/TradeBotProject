
package com.trade.bot.data.client.binance;

import com.binance.api.client.domain.market.Candlestick;
import com.trade.bot.CandleStickData;
import com.trade.bot.TradeData;

/**
 * @author Ozan Ay
 */
public class BinanceCandleStickDataAdapter implements CandleStickData {
    private TradeData openTradeData;
    private TradeData closeTradeData;
    private double high;
    private double low;
    private String volume;
    private String quoteAssetVolume;
    private Long numberOfTrades;
    private String takerBuyBaseAssetVolume;
    private String takerBuyQuoteAssetVolume;

    public BinanceCandleStickDataAdapter(Candlestick candlestick) {
        if (candlestick == null) {
            throw new IllegalArgumentException("Candlestick cannot be empty.");
        }

        this.openTradeData = new BinanceTradeDataAdapter(candlestick.getOpen(), candlestick.getOpenTime());
        this.closeTradeData = new BinanceTradeDataAdapter(candlestick.getClose(), candlestick.getCloseTime());
        this.high = Double.parseDouble(candlestick.getHigh());
        this.low = Double.parseDouble(candlestick.getLow());
        this.volume = candlestick.getVolume();
        this.quoteAssetVolume = candlestick.getQuoteAssetVolume();
        this.numberOfTrades = candlestick.getNumberOfTrades();
        this.takerBuyBaseAssetVolume = candlestick.getTakerBuyBaseAssetVolume();
        this.takerBuyQuoteAssetVolume = candlestick.getTakerBuyQuoteAssetVolume();
    }


    @Override
    public TradeData getOpenTradeData() {
        return this.openTradeData;
    }

    @Override
    public TradeData getCloseTradeData() {
        return this.closeTradeData;
    }

    @Override
    public double getHighPrice() {
        return this.high;
    }

    @Override
    public double getLowPrice() {
        return this.low;
    }

    @Override
    public String getVolume() {
        return this.volume;
    }

    @Override
    public String getQuoteAssetVolume() {
        return this.quoteAssetVolume;
    }

    @Override
    public Long getNumberOfTrades() {
        return this.numberOfTrades;
    }

    @Override
    public String getTakerBuyBaseAssetVolume() {
        return this.takerBuyBaseAssetVolume;
    }

    @Override
    public String getTakerBuyQuoteAssetVolume() {
        return this.takerBuyQuoteAssetVolume;
    }
}
