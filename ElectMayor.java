import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ElectMayor {
    static class InOutDegree {
        int inDegree;
        int outDegree;

        InOutDegree(int inDegree, int outDegree) {
            this.inDegree = inDegree;
            this.outDegree = outDegree;
        }
    };

    public static void main(String[] args) {

        BufferedReader br = null;
        FileWriter fw = null;
        Map<String, InOutDegree> map = new HashMap<>();

        try {
            br = new BufferedReader(new FileReader(new File("mayor.in")));
            fw = new FileWriter(new File("mayor.out"));

            String line = null;
            while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
                StringTokenizer st = new StringTokenizer(line, ", ");
                String name1 = st.nextToken();
                String name2 = st.nextToken();

                InOutDegree node1 = map.get(name1);
                if (node1 == null) {
                    node1 = new InOutDegree(0, 1);
                    map.put(name1, node1);
                } else {
                    node1.outDegree += 1;
                }
                InOutDegree node2 = map.get(name2);
                if (node2 == null) {
                    node2 = new InOutDegree(1, 0);
                    map.put(name2, node2);
                } else {
                    node2.inDegree += 1;
                }
            }
            for (Map.Entry<String, InOutDegree> entry : map.entrySet()) {
                if (entry.getValue().inDegree == (map.size() - 1) && entry.getValue().outDegree == 0) {
                    fw.write(entry.getKey());
                    break;
                }
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

