package com.trade.bot.data.client.binance;

import java.util.EnumMap;
import java.util.Map;

import com.binance.api.client.domain.market.CandlestickInterval;
import com.trade.bot.data.client.TradeClientCandleStickInterval;

/**
 * @author Ozan Ay
 */
class BinanceCandleStickIntervalConverter {
    private static final Map<TradeClientCandleStickInterval, CandlestickInterval> intervalConversionMap = new EnumMap<>(TradeClientCandleStickInterval.class);
    static {
        intervalConversionMap.put(TradeClientCandleStickInterval.ONE_MINUTE, CandlestickInterval.ONE_MINUTE);
        intervalConversionMap.put(TradeClientCandleStickInterval.THREE_MINUTES, CandlestickInterval.THREE_MINUTES);
        intervalConversionMap.put(TradeClientCandleStickInterval.FIVE_MINUTES, CandlestickInterval.FIVE_MINUTES);
        intervalConversionMap.put(TradeClientCandleStickInterval.FIFTEEN_MINUTES, CandlestickInterval.FIFTEEN_MINUTES);
        intervalConversionMap.put(TradeClientCandleStickInterval.HALF_HOURLY, CandlestickInterval.HALF_HOURLY);
        intervalConversionMap.put(TradeClientCandleStickInterval.HOURLY, CandlestickInterval.HOURLY);
        intervalConversionMap.put(TradeClientCandleStickInterval.TWO_HOURLY, CandlestickInterval.TWO_HOURLY);
        intervalConversionMap.put(TradeClientCandleStickInterval.FOUR_HOURLY, CandlestickInterval.FOUR_HOURLY);
        intervalConversionMap.put(TradeClientCandleStickInterval.SIX_HOURLY, CandlestickInterval.SIX_HOURLY);
        intervalConversionMap.put(TradeClientCandleStickInterval.EIGHT_HOURLY, CandlestickInterval.EIGHT_HOURLY);
        intervalConversionMap.put(TradeClientCandleStickInterval.TWELVE_HOURLY, CandlestickInterval.TWELVE_HOURLY);
        intervalConversionMap.put(TradeClientCandleStickInterval.DAILY, CandlestickInterval.DAILY);
        intervalConversionMap.put(TradeClientCandleStickInterval.THREE_DAILY, CandlestickInterval.THREE_DAILY);
        intervalConversionMap.put(TradeClientCandleStickInterval.WEEKLY, CandlestickInterval.WEEKLY);
        intervalConversionMap.put(TradeClientCandleStickInterval.MONTHLY, CandlestickInterval.MONTHLY);
    }

    private BinanceCandleStickIntervalConverter() {}

    static CandlestickInterval from(TradeClientCandleStickInterval interval) {
        return intervalConversionMap.get(interval);
    }
}
