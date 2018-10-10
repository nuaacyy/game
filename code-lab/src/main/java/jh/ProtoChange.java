package jh;

import java.io.*;

public class ProtoChange {

    public static void main(String[] args) {

        FileReader fr = null;
        BufferedReader br = null;
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            File file = new File("D://client2server.proto");
            File dest = new File("D://a.txt");
            if (!dest.exists()) {
                dest.createNewFile();
            }

            if (file.exists()) {
                fr = new FileReader(file);
                br = new BufferedReader(fr);
                fw = new FileWriter(dest);
                bw = new BufferedWriter(fw);

                String s;
                Boolean flag = false;
                while ((s = br.readLine()) != null) {
                    if (flag) {
                        if (s.contains("required ") && !s.contains(" rt")) {
                            s = s.replace("required ", "optional ");
                        }
                    }

                    if (s.matches("message\\s+.*Rt\\s*\\{")) {
                        flag = true;
                    }

                    if (flag && s.matches("}")) {
                        flag = false;
                    }

                    bw.write(s);
                    bw.newLine();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
