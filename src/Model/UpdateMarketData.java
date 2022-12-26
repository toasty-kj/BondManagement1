package Model;

import Controller.BondMaster;
import Controller.BondPosition;
import Model.CheckPosition;
import Model.LoadMaster;
import Model.LoadPosition;

import javax.print.attribute.HashAttributeSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.chrono.ThaiBuddhistChronology;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UpdateMarketData {
    /**
     * 値洗いを行うupdateMarketDataメソッド
     * @return 入力してもらった市場価格を反映した保有銘柄リストを返す
     * @throws IOException
     */
    public ArrayList<BondPosition>updateMarketData() throws IOException{
        LoadPosition loadPosition = new LoadPosition();
        ArrayList<BondPosition> bondPositionList = new ArrayList<>();
        bondPositionList = loadPosition.loadPosition();
        LoadMaster loadMaster = new LoadMaster();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        CheckPosition checkPosition = new CheckPosition();

        try {
            HashMap<String, BondMaster> masterMap = new HashMap<>();
            masterMap = loadMaster.loadBondMaster();

            System.out.println("値洗いを行います");
            while (true){
                for (int i=0; i < bondPositionList.size(); i++){
                    String ticker = bondPositionList.get(i).getTicker();
                    String issuer = masterMap.get(ticker).getIssuer();
                    if (bondPositionList.get(i).getAmount().compareTo(BigDecimal.ZERO)>0){
                        System.out.println(ticker +"[ "+issuer+" ]"+" の現在価格を入力してください。");
                        BigDecimal marketPrice = new BigDecimal(br.readLine());
                        BondPosition newPosition = new BondPosition(ticker, checkPosition.amountCheck(ticker), checkPosition.priceCheck(ticker), marketPrice);
                        bondPositionList.set(i, newPosition);
                    }

                }break;
            }

        }catch (IOException e){
            System.out.println(e);

        }return bondPositionList;
    }
}
