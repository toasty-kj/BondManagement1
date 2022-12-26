package View;

public class FuncPrint {
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
