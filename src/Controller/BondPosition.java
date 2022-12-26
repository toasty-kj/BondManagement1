package Controller;

import java.math.BigDecimal;

public class BondPosition {
    /**
     * 保有銘柄のクラス
     * 変数として銘柄コード, 数量, 簿価, 市場価格を持つ
     */
    private String ticker;
    private BigDecimal amount;
    private BigDecimal purchasedPrice;
    private BigDecimal marketPrice;

    public BondPosition(String ticker,BigDecimal  amount, BigDecimal purchasedPrice, BigDecimal marketPrice){
        this.ticker = ticker;
        this.purchasedPrice = purchasedPrice;
        this.marketPrice = marketPrice;
        this.amount = amount;
    }
    public String getTicker(){return ticker;}
    public BigDecimal getAmount(){return amount;}
    public BigDecimal getPurchasedPrice(){return purchasedPrice;}
    public BigDecimal getMarketPrice(){return marketPrice;}
}
