package eric.chatservice.ai;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Getfive {
    private String poem;


    private final String poemArray = "python C:/Users/Administrator/Desktop/java/chatboard/aichat/getfive.py  ";


    public String getPoem(String title) {
        Getfive getfive = new Getfive();
        getfive.cmd(poemArray + title);
        // System.out.println(getfive.poem);
        return getfive.poem;
    }


    public void cmd(String... cmds){
        InputStream in = null;
        String[] cmd = new String[]{"cmd.exe", "/C"};
        for (String s : cmds) {
            cmd[cmd.length - 1] += ", " + s;
        }

        try {
            Process process = Runtime.getRuntime().exec(cmd);
            in = process.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in, "GBK"));

            System.out.println("-----------------------------");

            String line = null;

            while ((line = read.readLine()) != null) {
                System.out.println(line);

                if(!"null".equals(line)) {
                    poem = poem + line + "\n";
                }
            }

            int exitValue = process.waitFor();
            System.out.println("返回值：" + exitValue);
            process.getOutputStream().close();       // 不要忘记了一定要关


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}


