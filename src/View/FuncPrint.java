package View;

public class FuncPrint {
    /**
     * 選択された機能の番号を引数に区切りとともに機能を表示する
     * @param funcInt
     */
    public void funcPrint(int funcInt){
        JobList jobList = new JobList();
        String[] joblist = jobList.jobList();
        String starting = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
        String ending = "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";

        System.out.println(starting);
        System.out.println(joblist[funcInt]);
        System.out.println(ending);
    }
}
