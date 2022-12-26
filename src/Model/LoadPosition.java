package Model;

import Controller.BondPosition;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LoadPosition {
    public ArrayList<BondPosition> loadPosition()throws IOException{
        String filePosition = "HoldingPosition.csv";
        BufferedReader br=null;
        String[] arrayStr = new String[4];
        ArrayList<BondPosition> bondPositionList = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader(filePosition, StandardCharsets.UTF_8));
            String message;
            while ((message = br.readLine()) != null) {
                arrayStr = message.split(",");
                String ticker = new String(arrayStr[0]);
                BigDecimal amount = new BigDecimal(arrayStr[1]);
                BigDecimal purchasedPrice = new BigDecimal(arrayStr[2]);
                BigDecimal marketPrice = new BigDecimal (BigInteger.ZERO);

                BondPosition line = new BondPosition(ticker, amount, purchasedPrice, marketPrice);
                bondPositionList.add(line);
            }
        }catch (IOException e){
            System.out.println("ファイルが見つかりません。");
        }finally {
            br.close();
        }return bondPositionList;
    }
}
