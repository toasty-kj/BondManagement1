package View;

import Controller.BondMaster;
import Controller.BondPosition;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

public class GetTable {
    public void getTable(ArrayList<BondPosition> bondPositionList, Map<String, BondMaster> masterMap) throws IOException {
        /**整形表示する表の中身を表示する。
         * 表示するのは銘柄コード,銘柄名、償還年月日、利率、クーポン回数、保有数量、簿価、時価、評価損益
         **銘柄コードからmapの銘柄名、年月、利率、クーポン回数を取り出して
         *Listのほうから保有数量、簿価、時価、評価損益を取り出す。
         * @param bondPositionList 保有銘柄リスト
         * @param masterMap マスターファイル
         */
        ValueNA.Separation separation = new ValueNA.Separation();
        GetTableHeader getTableHeader = new GetTableHeader();
        ValueNA valueNA = new ValueNA();
        DecimalFormat df = new DecimalFormat("###,###.#");
        DecimalFormat df2 = new DecimalFormat("###,###,###");
        df.setMinimumFractionDigits(3);
        String ticker;

        separation.sep();
        getTableHeader.getTableHeader();
        for (int i = 0; i < bondPositionList.size(); i++) {
            try {
                ticker = bondPositionList.get(i).getTicker();
                int maturity = masterMap.get(ticker).getMaturity();
                BigDecimal yield = masterMap.get(ticker).getYield().setScale(3, BigDecimal.ROUND_DOWN);
                int coupon = masterMap.get(ticker).getCoupon();
                String issuer = masterMap.get(ticker).getIssuer();
                BigDecimal amount = bondPositionList.get(i).getAmount().setScale(3, BigDecimal.ROUND_DOWN);
                BigDecimal purchasedPrice = bondPositionList.get(i).getPurchasedPrice().setScale(3, BigDecimal.ROUND_DOWN);
                BigDecimal marketPrice = bondPositionList.get(i).getMarketPrice().setScale(3, BigDecimal.ROUND_DOWN);
                BigDecimal subtract = new BigDecimal("0");
                BigDecimal profitLoss = new BigDecimal("0");

                if (!(amount.equals(BigDecimal.ZERO))) {
                    if (marketPrice.compareTo(profitLoss) > 0) {
                        subtract = marketPrice.subtract(purchasedPrice).setScale(3, BigDecimal.ROUND_DOWN);
                        profitLoss = subtract.multiply(amount).setScale(0, BigDecimal.ROUND_DOWN);
                        System.out.print("|");
                        System.out.print(String.format("%-15s", ticker) + "|");
                        System.out.print(issuer);

                        int length = 0;
                        char[] c = issuer.toCharArray();
                        for (int l = 0; l < c.length; l++) {
                            if (String.valueOf(c[l]).getBytes().length <= 1) {
                                length += 1;
                            } else {
                                length += 2;
                            }
                        }
                        for (int ite = 0; ite < 17 - length; ite++) {
                            System.out.print(" ");
                        }
                        System.out.print("|");
                        System.out.print(String.format("%-15s", maturity) + "|");
                        System.out.print(String.format("%9s", df.format(yield)) + "|");
                        System.out.print(String.format("%17s", coupon) + "|");
                        System.out.print(String.format("%13s", df2.format(amount)) + "|");
                        System.out.print(String.format("%9s", df.format(purchasedPrice)) + "|");
                        System.out.print(String.format("%9s", df.format(marketPrice)) + "|");
                        System.out.print(String.format("%12s", df2.format(profitLoss)));
                        System.out.println("|");
                    } else {
                        //N/A表示をするやつ
                        valueNA.valueNA(ticker, issuer, maturity, yield, coupon, amount, purchasedPrice);
                    }

                }
            } catch (NullPointerException e) {
                i++;
            }
        }
        //System.out.print(String.format("%-6s",));
        separation.sep();
    }
}
