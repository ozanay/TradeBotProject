
package com.trade.bot.data.client.binance;

import com.binance.api.client.domain.market.Candlestick;
import com.trade.bot.CandleStickData;

import java.util.Date;

/**
 * @author Ozan Ay
 */
public class BinanceCandleStickDataAdapter implements CandleStickData {
    private Date openTime;
    private double open;
    private double high;
    private double low;
    private double close;
    private String volume;
    private Date closeTime;
    private String quoteAssetVolume;
    private Long numberOfTrades;
    private String takerBuyBaseAssetVolume;
    private String takerBuyQuoteAssetVolume;

    public BinanceCandleStickDataAdapter(Candlestick candlestick) {
        if (candlestick == null) {
            throw new IllegalArgumentException("Candlestick cannot be empty.");
        }

        this.openTime = new Date(candlestick.getOpenTime());
        this.open = Double.parseDouble(candlestick.getOpen());
        this.high = Double.parseDouble(candlestick.getHigh());
        this.low = Double.parseDouble(candlestick.getLow());
        this.close = Double.parseDouble(candlestick.getClose());
        this.volume = candlestick.getVolume();
        this.closeTime = new Date(candlestick.getCloseTime());
        this.quoteAssetVolume = candlestick.getQuoteAssetVolume();
        this.numberOfTrades = candlestick.getNumberOfTrades();
        this.takerBuyBaseAssetVolume = candlestick.getTakerBuyBaseAssetVolume();
        this.takerBuyQuoteAssetVolume = candlestick.getTakerBuyQuoteAssetVolume();
    }


    @Override
    public Date getOpenTime() {
        return this.openTime;
    }

    @Override
    public double getOpenPrice() {
        return this.open;
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
    public double getClosePrice() {
        return this.close;
    }

    @Override
    public String getVolume() {
        return this.volume;
    }

    @Override
    public Date getCloseTime() {
        return this.closeTime;
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
