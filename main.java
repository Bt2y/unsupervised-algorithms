import java.util.ArrayList;
import java.lang.Object;

public class main{
    public static void main(String [] arg) {
    ArrayList<int[]> data = new ArrayList<int[]>();
    data.add(new int[]{1,1});
    data.add(new int[]{1,2});
    data.add(new int[]{2,1});
    data.add(new int[]{2,2});
    data.add(new int[]{4,5});
    data.add(new int[]{5,4});
    data.add(new int[]{4,4});
    data.add(new int[]{5,5});
    int[] g =new int[]{1,1};
    int[] i = new int[]{1,2};
    i = g;
    g[0] =4100000;
    System.out.print(i[0]);
    // int []p = new int[g.length+i.length](g,i);
    ArrayList<int[]> data2 = data;
    ArrayList<int[]> data3 = (ArrayList<int[]>)data.clone();
    // Utils.printer(data);
    // ArrayList<int[]> g = new ArrayList<int[]>();
    // g.add(new int[]{5,4});
    // g.add(new int[]{4,4});
    // g.add(new int[]{200,5});
    
    // data.addAll(g);
    K_Means a = new K_Means(2, data3);
    DBSCAN b = new DBSCAN(1, 3);
    ArrayList<int[]>km = a.k_means();
    b.Start(data2);

    // Aglomerative_Top_down c = new Aglomerative_Top_down(data);
    // c.start(5);
    }
}