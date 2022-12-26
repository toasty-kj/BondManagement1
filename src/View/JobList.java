package View;
public class JobList {
    /**
     * 機能のリストを返す
     * @return　機能のリスト
     */
    public String[] jobList(){
        String[] joblist;
        joblist = new String[4];
        joblist[0] = "保有銘柄残高一覧表示";
        joblist[1] = "在庫入力";
        joblist[2] = "値洗い";
        joblist[3] = "終了";

        return joblist;
    }

}
