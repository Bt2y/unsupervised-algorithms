import java.util.ArrayList;

public class DBSCAN{
    // enum points_classiffie {
    //     Noise ="Noise",
    // }
    
    private double eps;
    private int minPts;
    private String[] points_classiffier;
    private ArrayList<int[]> data;
    private int cluster_counter;
    private Utils util;

    public DBSCAN(double eps, int minPts){
        this.eps = eps; 
        this.minPts = minPts;
        util = new Utils();
    }


    public void Start(ArrayList<int[]> data){
        this.data = data;
        points_classiffier = new String[data.size()] ;
        cluster_counter = 0;

        set_undefined();
        for (int i = 0; i < data.size(); i++) {
            if(points_classiffier[i] != "undefined") continue;
            ArrayList<int[]> neighbors = RangeQuery(i);

            if(neighbors.size()+1< minPts){
                points_classiffier[i] = "Noise";
                continue;
            }

            cluster_counter ++;

            points_classiffier[i] = Integer.toString(cluster_counter);

            for (int k = 0; k < neighbors.size(); k++) {
                int j = neighbors.get(k)[0];
                if( points_classiffier[j] == "Noise") points_classiffier[j] = Integer.toString(cluster_counter);
                if (points_classiffier[j] != "undefined") continue;
                points_classiffier[j] = Integer.toString(cluster_counter);
                ArrayList<int[]> n = RangeQuery(j);
                if (n.size()+1 >= minPts) neighbors.addAll(n);
                
            }
        }
        util.printer(data);
        util.printerArrayStr(points_classiffier);
        
    }

    ArrayList<int[]> RangeQuery(int position){
        ArrayList<int[]> result = new ArrayList<int[]>();
        
        for (int i = 0; i < data.size() ; i++) {
            if (i == position) continue;
            double aux =util.EuclidianMetric(data.get(i), data.get(position)  );
            if(aux<= eps) 
                {result.add(new int[]{i});}
        }

        return result;
    }



    public void set_undefined(){
        for (int j = 0; j < points_classiffier.length;j++){
            points_classiffier[j] = "undefined";
        }
    }
}