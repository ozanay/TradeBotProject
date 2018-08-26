package com.trade.bot.data.decisionmaker;

import com.trade.bot.CandleStickData;
import com.trade.bot.TradeSymbol;
import com.trade.bot.data.client.TradeClient;
import com.trade.bot.data.client.TradeClientCandleStickInterval;
import com.trade.bot.data.client.TradeWebSocketClient;
import com.trade.bot.data.indicator.Indicator;

import java.util.List;

/**
 * @author Ozan Ay
 */
public class MavilimDecisionMaker implements CommercialDecisionMaker {
    private final Indicator indicator;
    private final TradeClient tradeClient;
    private final TradeWebSocketClient tradeWebSocketClient;
    private final TradeSymbol tradeSymbol;
    private final TradeClientCandleStickInterval candleStickInterval;

    public MavilimDecisionMaker(Indicator indicator, TradeClient tradeClient,
                                TradeWebSocketClient tradeWebSocketClient, TradeSymbol tradeSymbol,
                                TradeClientCandleStickInterval candleStickInterval) {
        this.indicator = indicator;
        this.tradeClient = tradeClient;
        this.tradeWebSocketClient = tradeWebSocketClient;
        this.tradeSymbol = tradeSymbol;
        this.candleStickInterval = candleStickInterval;
    }

    @Override
    public void decide() {

    }

    @Override
    public void warmUp() {
        List<CandleStickData> candleStickData = tradeClient.getCandleStickData(tradeSymbol, candleStickInterval);
        for (int index = 0; index < candleStickData.size() - 2; index++) {
            CandleStickData candleStick = candleStickData.get(index);
            indicator.warmUp(candleStick.getCloseTradeData());
        }
    }
}
