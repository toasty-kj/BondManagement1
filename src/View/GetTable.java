package View;

import Controller.BondMaster;
import Controller.BondPosition;
import Model.LoadMaster;
import Model.LoadPosition;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.String;

public class GetTable {
    public void  getTable(List<BondPosition> bondPositionList, Map<String, BondMaster> masterMap) throws IOException {
        //表示するのは銘柄コード,銘柄名、償還年月日、利率、クーポン回数、保有数量、簿価、時価、評価損益
        //銘柄コードからmapの銘柄名、年月、利率、クーポン回数を取り出して
        //Listのほうから保有数量、簿価、時価、評価損益を取り出す。
        Separation separation = new Separation();
        GetTableHeader getTableHeader = new GetTableHeader();
        LoadMaster loadMaster = new LoadMaster();
        LoadPosition loadPosition = new LoadPosition();
        DecimalFormat df = new DecimalFormat("###,###,###");
        String ticker;

        separation.sep();
        getTableHeader.getTableHeader();
        for (int i = 0; i<bondPositionList.size(); i++){
            try {
                ticker = bondPositionList.get(i).getTicker();
                int maturity = masterMap.get(ticker).getMaturity();
                BigDecimal yield = masterMap.get(ticker).getYield();
                int coupon = masterMap.get(ticker).getCoupon();
                String issuer = masterMap.get(ticker).getIssuer();
                BigDecimal amount = bondPositionList.get(i).getAmount();
                BigDecimal purchasedPrice = bondPositionList.get(i).getPurchasedPrice();
                BigDecimal marketPrice = bondPositionList.get(i).getMarketPrice();
                BigDecimal subtract = new BigDecimal("0");
                BigDecimal profitLoss = new BigDecimal("0");

                if (!(amount.equals(BigDecimal.ZERO))){
                    if (marketPrice.compareTo(profitLoss)>0){
                        subtract = marketPrice.subtract(purchasedPrice);
                        profitLoss = subtract.multiply(amount);
                    }

                    System.out.print("|");
                    System.out.print(String.format("%-15s",ticker)+"|");
                    System.out.print(issuer);

                    int length = 0;
                    char[] c = issuer.toCharArray();
                    for (int l=0; l<c.length; l++){
                        if (String.valueOf(c[l]).getBytes().length<=1){
                            length += 1;
                        }else {
                            length += 2;
                        }
                    }
                    for (int ite=0; ite<17-length; ite++){
                        System.out.print(" ");
                    }
                    System.out.print("|");
                    System.out.print(String.format("%15s",maturity)+"|");
                    System.out.print(String.format("%-9s",yield)+"|");
                    System.out.print(String.format("%17s",coupon)+"|");
                    System.out.print(String.format("%-13s",df.format(amount))+"|");
                    System.out.print(String.format("%-9s", df.format(purchasedPrice))+"|");
                    System.out.print(String.format("%-9s",df.format(marketPrice))+"|");
                    System.out.print(String.format("%-8s",df.format(profitLoss)));
                    System.out.println("|");

                }
            }catch (NullPointerException e){
                i++;
            }
        }
        //System.out.print(String.format("%-6s",));
        separation.sep();
    }

}
