class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] ref = new boolean[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(matrix[i][j] == 0){
                    for(int c=0; c<n; c++){
                        ref[i][c] = true;
                    }

                    for(int r=0;r<m; r++){
                        ref[r][j] = true;
                    }
                }
            }
        }

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(ref[i][j] == true){
                    matrix[i][j] = 0;
                }
            }
        }

        
    }
}
