package Model;

import Controller.BondPosition;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ReWriteCSV {
    public void reWriteCSV(ArrayList<BondPosition> bondPositionList) throws IOException {
        FileWriter fw = new FileWriter("HoldingPosition.csv");
        PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
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
        pw.close();
        fw.close();
    }
}
