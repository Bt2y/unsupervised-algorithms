import java.util.ArrayList;

public class Utils{
    
    public double EuclidianMetric(int[] x_1, int[] x_2){
        double result = 0;
        for (int i = 0; i < x_1.length; i++) {
            result += Math.pow(x_1[i]-x_2[i], 2);
        }
        return Math.sqrt(result);
        
    }

    public void printer(ArrayList<int[]> l){
        System.out.print("[ ");
        for (int i = 0; i<l.size();i++){
            System.out.print("[ ");
            for (int j = 0; j < l.get(i).length;j++){
                System.out.print((l.get(i))[j] + " ");
            }
            System.out.print("]");
        }
        System.out.print(" ]\n");
    }

    public void printerArray(int[] array){
        System.out.print("[ ");
        for (int j = 0; j < array.length;j++){
            System.out.print(array[j] + " ");
        }
        System.out.print("]\n");
    }

    // TODO: Cual es el tipo generico de iny y string y como utilizarlo
    public void printerArrayStr(String[] array){
        System.out.print("[ ");
        for (int j = 0; j < array.length;j++){
            System.out.print(array[j] + " ");
        }
        System.out.print("]\n");
    }
    
    public int[] Unit_thow_arrays(int[] arr1, int[] arr2){
        int[] result = new int[arr1.length+arr2.length];

        for (int i = 0; i < arr1.length; i++) {
            result[i] = arr1[i];
        }
        for (int i = 0; i < arr2.length; i++) {
            result[arr1.length + i] = arr2[i];
        }
        return result;
    }
    public ArrayList<int[]> Split_in_groups(int[] groups){
        ArrayList<int[]> result = new ArrayList<int[]>();

        int cant_groups =-1;
        int[] cont_for_groups = new int[groups.length];

        for (int i = 0; i < groups.length; i++) {
            if(cont_for_groups[ groups[i]]>0){
                cont_for_groups[groups[i]]++;
            }
            else{
                cant_groups++;
                cont_for_groups[groups[i]]++;
            }
        }

        for (int i = 0; i < cant_groups+1; i++) {
            result.add(new int[cont_for_groups[i]]);
        }
        
        cont_for_groups = new int[groups.length];

        for (int i = 0; i < groups.length; i++) {
            result.get(groups[i])[cont_for_groups[groups[i]]] = i;
            cont_for_groups[groups[i]]++;
        }
        return result;
    }
}