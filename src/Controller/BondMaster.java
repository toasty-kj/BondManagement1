package Controller;
import java.math.BigDecimal;


public class BondMaster {
    /**
     * 債券マスターファイルのクラス
     * 変数として銘柄コード, 銘柄名, 利回り, 満期, 利払い回数をもつ
     */
    private String ticker;
    private String issuer;
    private BigDecimal yield;
    private int maturity;
    private int coupon;

    public BondMaster(String ticker, String issuer, BigDecimal yield, int maturity, int coupon){
        this.ticker = ticker;
        this.issuer = issuer;
        this.yield = yield;
        this.maturity = maturity;
        this.coupon = coupon;
    }

    public String getIssuer(){return issuer;}
    public BigDecimal getYield(){return yield;}
    public int getMaturity(){return maturity;}
    public int getCoupon(){return coupon;}
    public String getTicker(){return ticker;}

}