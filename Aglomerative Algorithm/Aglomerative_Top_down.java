import java.util.ArrayList;

public class Aglomerative_Top_down {

    private ArrayList<int[]> groups_indexs;
    private ArrayList<int[]> groups_representant;
    private ArrayList<int[]> data;
    private Utils util;
    public Aglomerative_Top_down( ArrayList<int[]> data) {
        this.data = data;
        util = new Utils();
        
        groups_indexs = new ArrayList<int[]>();
        Initialize_groups_indexs();
        
    }

    public void start(int nivel){
        double bigest_distance_in_group = Double.MIN_VALUE;
        int index_group_to_split = -1;
        K_Means kmeans = new K_Means(2, data);

        groups_indexs = new ArrayList<int[]>();
        ArrayList<int[]> km = kmeans.k_means();
        groups_indexs.add( km.get(0));
        groups_indexs.add( km.get(1));
        for (int g = 0; g < nivel; g++) {
            for (int i = 0; i < groups_indexs.size(); i++) {
                for (int j = 0; j < groups_indexs.get(i).length; j++) {
                    for (int k = 0; k < groups_indexs.get(i).length; k++) {
                        if(j==k) continue;
                        double local_distance = util.EuclidianMetric(data.get(groups_indexs.get(i)[j]), data.get(groups_indexs.get(i)[k]));
                        if(local_distance > bigest_distance_in_group){
                            bigest_distance_in_group = local_distance;
                            index_group_to_split = i;
                        }
                    }
    
                }
            }
            ArrayList<int[]> data_group = Create_Data(groups_indexs.get(index_group_to_split));
            K_Means kmean = new K_Means(2, data_group);
            if(bigest_distance_in_group == 0) break;
            
            km = kmean.k_means();
            groups_indexs.add( km.get(0));
            groups_indexs.add( km.get(1));
            groups_indexs.remove(index_group_to_split);
            bigest_distance_in_group = -Double.MAX_VALUE;
            index_group_to_split = -1;
        }
        

        util.printer(groups_indexs);
        
    }
    private void Initialize_groups_indexs(){
        int[] group =new int[data.size()];
        for (int i = 0; i < data.size(); i++) {
            group[i] = i;
        }
        groups_indexs.add(group);
    }

    private ArrayList<int[]> Create_Data(int[] indexs){
        ArrayList<int[]> result = new ArrayList<int[]>();

        for (int i = 0; i < indexs.length; i++) {
            result.add(data.get(indexs[i]));
            
        }

        return result;
    }
    private int[] Choose_representant(int[] indexs_candidats){
        int[] result = new int[data.get(0).length];
        double minner_similarity = Double.MAX_VALUE;
        
        for (int i = 0; i < indexs_candidats.length ; i++) {
            for (int j = 0; j < indexs_candidats.length ; j++) {
                if(i==j)continue;
                double local_similarity = util.EuclidianMetric(data.get(indexs_candidats[i]), data.get(indexs_candidats[j]));
                if(local_similarity < minner_similarity){
                    result = data.get(indexs_candidats[i]);
                    minner_similarity = local_similarity;
                }
            }
        }


        return result;
    }
}