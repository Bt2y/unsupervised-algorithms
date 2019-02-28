import java.util.ArrayList;

public class Aglomerative_Button_up{
    
    private ArrayList<int[]> groups_indexs;
    private ArrayList<int[]> groups_representant;
    private ArrayList<int[]> data;
    private Utils util;

    public Aglomerative_Button_up( ArrayList<int[]> data) {
        this.data = (ArrayList<int[]>)data.clone();
        groups_representant = (ArrayList<int[]>)data.clone();
        groups_indexs = new ArrayList<int[]>();
        Initialize_groups_indexs();
        util = new Utils();
    }

    public void start(int nivel){
        for (int k = 0; k < nivel; k++) {
            double _most_similar = Double.MAX_VALUE;
            int _index_of_group_most_similar_i = -1;
            int _index_of_group_most_similar_j = -1;
            
            for (int i = 0; i < groups_representant.size(); i++) {
                for (int j = 0; j < groups_representant.size(); j++) {
                    if( i == j) continue;
                    double local_similar = util.EuclidianMetric(groups_representant.get(i), groups_representant.get(j));
                    if(local_similar < _most_similar){
                        _most_similar = local_similar;
                        _index_of_group_most_similar_i = i;
                        _index_of_group_most_similar_j = j;
                    }
                }
            }
            int[] aux = util.Unit_thow_arrays(groups_indexs.get(_index_of_group_most_similar_i), groups_indexs.get(_index_of_group_most_similar_j));
            groups_indexs.set(_index_of_group_most_similar_i, aux);
            groups_indexs.remove(_index_of_group_most_similar_j);
            
            aux = Choose_representant(groups_indexs.get(_index_of_group_most_similar_i));
            groups_representant.set(_index_of_group_most_similar_i, aux);
            groups_representant.remove(_index_of_group_most_similar_j);
        }

        util.printer(groups_indexs);
        
    }
    private void Initialize_groups_indexs(){

        for (int i = 0; i < data.size(); i++) {
            groups_indexs.add(new int[]{i});
        }
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