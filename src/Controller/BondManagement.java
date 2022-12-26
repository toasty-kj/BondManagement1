package Controller;

import Model.*;
import View.FuncPrint;
import View.GetTable;
import View.JobList;
import View.MenuSel;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class BondManagement {
    public static void main(String[] args) throws IOException {
        BondManagement bondManagement = new BondManagement();
        bondManagement.mainLoop();
    }
    public void mainLoop()throws IOException {
        JobList jobList = new JobList();
        ArrayList<BondPosition> bondPositionList = new ArrayList<>();
        HashMap<String, BondMaster> masterMap = new HashMap<>();
        LoadMaster loadMaster = new LoadMaster();
        masterMap = loadMaster.loadBondMaster();
        LoadPosition loadPosition = new LoadPosition();
        bondPositionList = loadPosition.loadPosition();
        UpdateMarketData updateMarketData = new UpdateMarketData();
        ReWriteCSV reWriteCSV = new ReWriteCSV();
        FuncPrint funcPrint = new FuncPrint();

        InputLoop inputLoop = new InputLoop();
        GetTable getTable = new GetTable();
        MenuSel menuSel = new MenuSel();

        System.out.println("債権の商品在庫管理システム");
        String[] joblist = jobList.jobList();
        String starting = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
        String ending = "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";
        String masterData = "MasterData.csv";
        String holdingPosition = "HoldingPosition.csv";

        Integer funcInt;
        boolean NumberFormatException = false;
        while (true) {
            try {
                funcInt = menuSel.menuSel();
                System.out.println(funcInt);
                BufferedReader br = null;
                switch (funcInt) {
                    case 0:
                        /**<保有銘柄残高一覧表示>
                        //holdingPosition.csv を読み込んで整形表示する
                        getTableで書き出す*/
                        getTable.getTable(bondPositionList, masterMap);
                        break;
                    case 1:
                        /**<在庫入力>
                         入力後保有銘柄リストに返す
                         */
                        bondPositionList = inputLoop.inputLoop(funcInt);
                        reWriteCSV.reWriteCSV(bondPositionList);
                        break;
                    case 2:
                        /**値洗い
                         市場価格を入力後リストに返す。
                         csvには反映させない。
                         */
                        funcPrint.funcPrint(funcInt);
                        bondPositionList = updateMarketData.updateMarketData();
                        break;
                    case 3:
                        /**終了
                         */
                        System.out.println(starting);
                        System.out.println(joblist[funcInt]);
                        reWriteCSV.reWriteCSV(bondPositionList);
                        System.out.println(ending);
                        System.out.println("処理が完了しました。");
                        break;
                }
                if (funcInt == 3){
                    break;
                }
            } catch (NumberFormatException e) {
            System.out.println("0~3の半角を入力してください。");
                continue;
            }if (funcInt>3){
                System.out.println("0~3の半角を入力してください。");
            }
        }
    }
}
