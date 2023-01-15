package Model;

import Controller.BondMaster;
import Controller.BondPosition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InputAmount {
    /**
     * 在庫入力時の数量入力メソッド
     * 引数の銘柄コードをすでに持っている場合は売り買いで場合分け、持っていない場合は売りのみ
     * @param ticker 銘柄コード
     * @param buysell 売り買いの選択
     * @return 買ったもしくは売った数量
     * @throws IOException
     */
    public BigDecimal inputAmount(String ticker, int buysell) throws IOException {
/**
 * 在庫入力時の数量の入力
 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LoadMaster loadMaster = new LoadMaster();
        HashMap<String, BondMaster> masterMap = new HashMap<>();
        LoadPosition loadPosition = new LoadPosition();
        ArrayList<BondPosition> bondPositionList = new ArrayList<>();
        bondPositionList = loadPosition.loadPosition();
        CheckPosition checkPosition = new CheckPosition();
        BigDecimal amount;
        /**もし銘柄を持っていないときは買いのみのため売りは考えない*/
        while (true) {
            System.out.println("数量を入力してください。");
            try {
                masterMap = loadMaster.loadBondMaster();
                amount = new BigDecimal(br.readLine());
                if (checkPosition.checkExist(ticker, bondPositionList)){
                    BigDecimal currentAmount = bondPositionList.get(checkPosition.getNumRow(ticker)).getAmount();
                    //もしcurrent amountがamoutを下回っていたら。
                    if (buysell == 1) {
                        //買いの場合
                        break;
                    }
                    if (buysell == 2) {
                        if (currentAmount.compareTo(amount) >= 0) {
                            break;
                        } else {
                            System.out.println("保有数量分しか売却する減らせません。");
                            System.out.println("保有数量 : " + currentAmount);
                            continue;
                        }
                    }
                }else {break;}

            } catch (IOException e) {
                continue;
            } catch (NumberFormatException e) {
                System.out.println("半角数字を入力してください。");
            }
        }
        return amount;
    }
}
