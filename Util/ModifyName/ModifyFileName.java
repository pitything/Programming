package Util.ModifyName;

import java.io.File;

public class ModifyFileName {
    public static void main(String[] args) {
        String dirStr = "/Volumes/江豪L/Study/练字/吴玉生行楷";
        File dir = new File(dirStr);
        File[] files = dir.listFiles();
        for (File file : files) {
            String destName = file.getName().replace("华夏万卷吴玉生硬笔行楷字帖配套视频教程（最新版） - ",
                    "");
            file.renameTo(new File(dirStr + File.separator + destName));
        }
    }
}
