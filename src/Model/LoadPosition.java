package Model;

import Controller.BondPosition;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class LoadPosition {
    /**
     * 保有銘柄のcsvファイルを読み込んでArrayListに格納して返す
     *
     * @return 保有銘柄リスト
     * @throws IOException
     */
    public ArrayList<BondPosition> loadPosition() throws IOException {
        String filePosition = "HoldingPosition.csv";
        BufferedReader br = null;
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
                BigDecimal marketPrice = new BigDecimal(arrayStr[3]);

                BondPosition line = new BondPosition(ticker, amount, purchasedPrice, marketPrice);
                bondPositionList.add(line);
            }
        } catch (IOException e) {
            System.out.println("ファイルが見つかりません。");
        } finally {
            br.close();
        }
        return bondPositionList;
    }
}
