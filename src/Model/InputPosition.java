package Controller;


import Model.CheckPosition;
import Model.LoadMaster;
import Model.LoadPosition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InputPosition {
    public int inputBuySell(){
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        Integer buysell = 0;
        while (true){
            System.out.println("買いなら1,売りなら2を入力してくだい");
            try {
                buysell = Integer.parseInt(br.readLine());
                if (buysell<0 || buysell>3){
                    continue;
                }else {break;}
            } catch (NumberFormatException e){
                System.out.println("半角で入力してください。");
                continue;
            }catch (IOException e){
                System.out.println(e);
                continue;
            }
        }return buysell;
    }
    public BigDecimal inputPrice(int buysell) {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        BigDecimal price;
        while (true){
            if (buysell == 1) {
                System.out.println("購入金額を入力してください。");
            }
            else if (buysell == 2){
                System.out.println("売却価格を入力してください。");
            }
            try {
                price = new BigDecimal(br.readLine());
                if (price.compareTo(new BigDecimal("0"))>0 && price.compareTo(new BigDecimal("999999"))<0 ){break;}
            }catch (IOException e){
                continue;
            }catch (NumberFormatException e){
                System.out.println("半角で購入金額を入力してください。");
                continue;
            }
        }
        return price;
    }
    public String inputTicker(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LoadMaster loadMaster = new LoadMaster();
        HashMap<String, BondMaster> masterMap = new HashMap<>();

        String ticker = null;
        while (true){
            try {
                System.out.println("ティッカーを入力してください。");
                ticker = br.readLine();
                masterMap = loadMaster.loadBondMaster();
                if (masterMap.containsKey(ticker)){
                    break;
                }else {
                    System.out.println("マスターファイルに存在しません");
                    continue;
                }
            }catch (IOException e){
                continue;
            }catch (NumberFormatException e){
                System.out.println("半角でティッカーを入力してください。");
            }
        }return ticker;
    }

    public BigDecimal inputAmount(String ticker, int buysell) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LoadMaster loadMaster = new LoadMaster();
        HashMap<String, BondMaster> masterMap = new HashMap<>();
        LoadPosition loadPosition = new LoadPosition();
        List<BondPosition> bondPositionList = new ArrayList<>();
        bondPositionList = loadPosition.loadPosition();
        CheckPosition checkPosition = new CheckPosition();
        BigDecimal amount;
        BigDecimal currentAmount = bondPositionList.get(checkPosition.getNumRow(ticker)).getAmount();
        while (true) {
            System.out.println("数量を入力してください。");
            try {

                masterMap = loadMaster.loadBondMaster();
                amount = new BigDecimal(br.readLine());
                //もしcurrent amountがamoutを下回っていたら。
                amount = new BigDecimal(br.readLine());
                if (buysell == 1) {
                    //買いの場合
                    amount = amount.add(currentAmount);
                    break;
                }
                if (buysell == 2) {
                    if (currentAmount.compareTo(amount) > 0) {
                        amount = currentAmount.subtract(amount);
                        break;
                    } else {
                        System.out.println("保有残高分しか売却することができません");
                        System.out.println("保有残高 : " + currentAmount);
                        continue;
                    }
                }
            } catch (IOException e) {
                continue;
            } catch (NumberFormatException e) {
                System.out.println("半角数字を入力してください。");
            }
        }
        return amount;
    }
}
