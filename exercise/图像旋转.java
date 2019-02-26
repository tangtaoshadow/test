给定一个 n × n 的二维矩阵表示一个图，将图像顺时针旋转 90 度。



/***
 * @author              tangtao  hangzhou HDU
 * @date                2019-2-27 01:43:46
 * @email               <tangtao16011324@outlook.com>
 * @function            
 * @achieve            
 * @returns             
 */


	思路
00 01 02 03
10 11 12 13
20 21 22 23
30 31 32 33

很明显 ，顺时针旋转90度 那么00应该到03 ;03应该到33 ;33应该到30; 30 应该到00;
同样第二层 01到13；13到32；32到20；20到01；
第三层02，23,31,10

ES6代码



let rotate=function (matrix){
    let len = matrix.length;
    for (let i = 0; i < len / 2; i++) {
        let start = i;
        let end = len - i - 1;
        for (let j = 0; j < end - start; j++) {
            consolelog('temp = matrix['+start+']['+start + j+']');
            let temp = matrix[start][start + j];
            consolelog('matrix['+start+']['+(start + j)+'] = matrix['+(end - j)+']['+start+'];');
            matrix[start][start + j] = matrix[end - j][start];
            consolelog('matrix['+(end - j)+']['+start+'] = matrix['+end+']['+(end - j)+'];');
            matrix[end - j][start] = matrix[end][end - j];
            consolelog('matrix['+end+']['+(end - j)+'] = matrix['+(start + j)+']['+end+'];');
            matrix[end][end - j] = matrix[start + j][end];
            consolelog('matrix['+(start + j)+']['+end+'] = temp;');
            matrix[start + j][end] = temp;
        }
    }
    return matrix;
}

let arr=[[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]];
consolelog(arr);
let arr1=rotate(arr);
consolelog(arr1);



	执行结果


1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16
temp = matrix[0][00]
matrix[0][0] = matrix[3][0];
matrix[3][0] = matrix[3][3];
matrix[3][3] = matrix[0][3];
matrix[0][3] = temp;
temp = matrix[0][01]
matrix[0][1] = matrix[2][0];
matrix[2][0] = matrix[3][2];
matrix[3][2] = matrix[1][3];
matrix[1][3] = temp;
temp = matrix[0][02]
matrix[0][2] = matrix[1][0];
matrix[1][0] = matrix[3][1];
matrix[3][1] = matrix[2][3];
matrix[2][3] = temp;
temp = matrix[1][10]
matrix[1][1] = matrix[2][1];
matrix[2][1] = matrix[2][2];
matrix[2][2] = matrix[1][2];
matrix[1][2] = temp;
13,9,5,1,14,10,6,2,15,11,7,3,16,12,8,4

	附上Java代码

public void rotate(int[][] matrix) {
        int len = matrix.length;
        for (int i = 0; i < len / 2; i++) {
            int start = i;
            int end = len - i - 1;
            for (int j = 0; j < end - start; j++) {
                int temp = matrix[start][start + j];
                matrix[start][start + j] = matrix[end - j][start];
                matrix[end - j][start] = matrix[end][end - j];
                matrix[end][end - j] = matrix[start + j][end];
                matrix[start + j][end] = temp;
            }
        }
    }




	还有一种思路
翻转 90° 就相当于 先关于 X 轴对称翻转 ， 再关于对角线翻转



public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length / 2; i++) {
            swap(matrix, i, matrix.length - i - 1);
        }
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < x; y++) {
                swap(matrix, x, y, y, x);
            }
        }
    }

    private void swap(int[][] matrix, int r1, int r2) {
        int[] temp = matrix[r1];
        matrix[r1] = matrix[r2];
        matrix[r2] = temp;
    }

    private void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
        int temp = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = temp;
    }




