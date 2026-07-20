class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // rotate an array 
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // reverse the every row
        for(int r=0; r<n; r++){
            int i=0, j=n-1;
            while(i<j){
                int temp = matrix[r][i];
                matrix[r][i] = matrix[r][j];
                matrix[r][j] = temp;
                i++;
                j--;
            }
        }

        // return matrix;
    }
}