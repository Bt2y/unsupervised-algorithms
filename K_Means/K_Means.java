import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;
import java.lang.Math;


public class K_Means{
    private ArrayList<int[]> data;
    private ArrayList<int[]> centers;
    private int k;
    private int[] groups;
    private Utils util;

    public K_Means(int clusters, ArrayList<int[]> dt){
        data = dt;
        k =clusters;
        groups = new int[data.size()];
        
        Random rn = new Random();
        int[] _initials_center_points = rn.ints(k, 0,data.size()).toArray();
        centers = Initialize_centers( _initials_center_points);
        util = new Utils();
        }

    public ArrayList<int[]> k_means( ){
        
        Assign();
        ArrayList<int[]> new_centers = Update();
        
        while(Keeps_Changing(new_centers, centers)) {
           centers = new_centers;
           Assign();
           new_centers = Update();
        } 

        util.printer(data);
        util.printerArray(groups);
        ArrayList<int[]> result = new ArrayList<int[]>();
        result = util.Split_in_groups(groups);
       return result;

    }

    private void  Assign(){
        double _minner_dist = Double.MAX_VALUE;
        int _group_with_minner_dist = 0;
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < centers.size(); j++) {
            
                double dist_to_group_j = util.EuclidianMetric(data.get(i), centers.get(j));
                if( dist_to_group_j < _minner_dist){
                    _minner_dist =dist_to_group_j;
                    _group_with_minner_dist = j;
                }
            }
            groups[i]=_group_with_minner_dist;
            
            _minner_dist = Double.MAX_VALUE;
            _group_with_minner_dist = 0;
        }
    }

    // Inicializar los arrays
    private ArrayList<int[]> Update(){
        ArrayList<int[]> _new_centers = Initialize_ArrayList(centers.size(), data.get(0).length);
        int[] __count_of_elements_in_centers = new int[centers.size()];

        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).length; j++) {

                _new_centers.get(groups[i])[j] += data.get(i)[j];
            }
            __count_of_elements_in_centers[groups[i]] ++;
        }

        for (int i = 0; i < _new_centers.size(); i++) {
            for (int j = 0; j < _new_centers.get(i).length; j++) {
                _new_centers.get(i)[j] /= __count_of_elements_in_centers[i];
            }
        }

        return _new_centers;

    }

    private ArrayList<int[]> Initialize_ArrayList( int length_arrayList, int length_array){

        ArrayList <int[]> result = new ArrayList<int[]>();
        for (int i = 0; i < length_arrayList; i++) {
            result.add(new int[length_array]);
        }
        return result;
    }

    private ArrayList<int[]> Initialize_centers( int[] centers_index){

        ArrayList <int[]> result = new ArrayList<int[]>();
        for (int i = 0; i < centers_index.length; i++) {
            result.add(data.get(centers_index[i]));
        }
        return result;
    }

    boolean Keeps_Changing(ArrayList<int[]> new_centers, ArrayList<int[]> centers){
        for (int i = 0; i < new_centers.size(); i++) {
            for (int j = 0; j < new_centers.get(i).length; j++) {
                if(new_centers.get(i)[j]!=centers.get(i)[j]){
                    return true;
                }
            }
        }
        return false;
    }


}