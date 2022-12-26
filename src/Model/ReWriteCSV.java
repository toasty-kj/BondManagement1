package Model;

import Controller.BondPosition;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ReWriteCSV {
    /**
     * 保有銘柄リストを実際のcsvファイルに反映させる
     * @param bondPositionList 保有銘柄リスト
     * @throws IOException
     */
    public void reWriteCSV(ArrayList<BondPosition> bondPositionList) throws IOException {
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter("HoldingPosition.csv", StandardCharsets.UTF_8);
            pw = new PrintWriter(new BufferedWriter(fw));
            for (int i = 0; i<bondPositionList.size(); i++) {
                try {
                    String ticker = bondPositionList.get(i).getTicker();
                    BigDecimal amount = bondPositionList.get(i).getAmount();
                    BigDecimal purchasedPrice = bondPositionList.get(i).getPurchasedPrice();
                    BigDecimal marketPrice = bondPositionList.get(i).getMarketPrice();
                } catch (NullPointerException e) {
                    i++;
                }
                if (i != 0){fw = new FileWriter("HoldingPosition.csv", true);}
                pw.print(bondPositionList.get(i).getTicker()+","+bondPositionList.get(i).getAmount()+","+
                        bondPositionList.get(i).getPurchasedPrice()+","+bondPositionList.get(i).getMarketPrice());
                pw.println();
            }
        }finally {
            pw.close();
            fw.close();

        }
            }
}
