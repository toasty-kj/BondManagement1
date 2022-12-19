package View;

import Controller.BondMaster;
import Controller.BondPosition;
import Model.LoadMaster;
import Model.LoadPosition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetTable {
    public void  getTable(List<BondPosition> bondPositionList, Map<String, BondMaster> masterMap) throws IOException {
        //表示するのは銘柄コード,銘柄名、償還年月日、利率、クーポン回数、保有数量、簿価、時価、評価損益
        //銘柄コードからmapの銘柄名、年月、利率、クーポン回数を取り出して
        //Listのほうから保有数量、簿価、時価、評価損益を取り出す。
        Separation separation = new Separation();
        GetTableHeader getTableHeader = new GetTableHeader();
        LoadMaster loadMaster = new LoadMaster();
        LoadPosition loadPosition = new LoadPosition();
        List<BondPosition> bondPositionList1 = new ArrayList<>();
        HashMap<String, BondMaster> masterHashMap = new HashMap<>();
        bondPositionList1 = loadPosition.loadPosition();
        masterHashMap = loadMaster.loadBondMaster();
        String ticker = bondPositionList1.get(0).getTicker();

        System.out.println(masterHashMap.get(ticker));


        getTableHeader.getTableHeader();
        System.out.print("|");

        //System.out.print(String.format("%-6s",));
        separation.sep();
    }
}
