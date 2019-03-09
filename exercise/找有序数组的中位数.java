找有序数组的中位数
唐涛 杭州电子科技大学 tangtao16011324@outlook.com  
2019-3-7 16:22:27

给定两个大小为 m 和 n 的有序数组 nums1 和 nums2；找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))，假设 nums1 和 nums2 不会同时为空。


	首先，理解中位数

将一个集合划分为两个长度相等的子集，其中一个子集中的元素总是大于另一个子集中的元素。

	我们将数组AB划分为两部分：
left_A            |        right_A
A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]

left_B             |        right_B
B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]

	注意
其中A有m个元素，那么他就有m+1中划分方法；i=0~m;
len(left_A)=i,len(right_A)=m−i.
注意：当i=0时，left_A为空集，当i=m时，right_A为空集。

	将AB的左右分别合并
left_part          |        right_part
A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]



	如果满足如下条件
	len(left_part)=len(right_part)
	max(left_part)<=min(right_part)
那么，我们已经将 {A,B} 中的所有元素划分为相同长度的两个部分，且其中一部分中的元素总是大于另一部分中的元素。
	那么median=(max(left_part)+min(right_part))/2
	

	要想满足上面条件，我们需要满足下面条件
	i+j=m-i+n-j或者m-i+n-j+1，如果n>=m,只需i=0~m,j=(m+n+1)/2-I;
	B[j-1]<=A[i]以及A[i-1]<=B[j]



	先解释一下为什么要n>=m ?
由于0<=i<=m 并且 j=(m+n+1)/2-i , 我们必须保证j>=0，如果n<m,就可能出现j<0；数组下标一定要大于等于0；这种情况是不允许出现的。


	接下来我们不考虑极端情况，即A[i-1] ,B[j-1] ,A[i] ,B[j]总是存在


	所以我们需要做的只是
在[0，m]中搜索并找到目标对象i，使得：
B[j-1]<=A[i]且  A[i−1]≤B[j], 其中 j = (m+n+1)/2−i


	我们用二叉树进行搜索
	1 设imin=0，imax=m, 然后开始在[imin,imax]中进行搜索。
	2 i = (min+imax)/2;j = (m + n + 1)/2 - i
	3 现在我们得到了len(left_part)=len(right_part)，但是我们只会遇到三种情况：
	3-1  B[j-1]<=A[i]且  A[i−1]≤B[j]  我们找到了目标对象，停止搜索
	3-2  B[j-1]>A[i]  这说明此时A[i] 太小，需要增大i，重新调整iMin,使得iMin=i+1;那么新范围变成[i+1,iMax] , 跳转到第二步执行。
	3-3  A[i−1]>B[j]  这说明 A[i-1] 太大，需要减小i，iMax=i-1;范围变为[iMin,i-1],再跳转至2，继续执行
	


	当找到目标对象i时，中位数为

max(A[i−1],B[j−1]), 当m+n 为奇数时
(max(A[i−1],B[j−1])+ min(A[i],B[j]))/2, 当m+n 为偶数时




	现在，让我们来考虑这些临界值 
i=0,i=m,j=0,j=n此时A[i−1],B[j−1],A[i],B[j] 可能不存在。这种情况要更容易

我们需要做的是确保max(left_part) < min(right_part)。 因此, 如果i和j不是临界值(这意味着A[i一1],B[j- 1],A[i],B[j]全部存在) , 那么我们必须同时检查B[j- 1]≤A[i]以及A[i- 1]<=B[j]是否成立。但是如果A[i- 1],B[j- 1], A[i],B[j]中部分不存在，那么我们只需要检查这两个条件中的一个(或不需要检查)。举个例子,如果i=0,那么A[i- 1]不存在，我们就不需要检查A[i- 1]< B[j]是否成立。所以，我们需要做的是:


在[0，m]中搜索并找到目标对象i，以使：
 (j=0 or i=m or  B[j−1]≤A[i]) 或是 (i=0 or j=n or A[i−1]≤B[j]), 其中 j = (m+n+1)/2−i


	总结一下

在循环搜索中，我们只会遇到三种情况：

	1 (j=0 or i= m or B[j- 1]< A[i)或是(i=0 or j=n or A[i-1]≤B[j])这意味着i是完美的，我们可以停止搜索。
	2 j>0 and i < m and B[j- 1]> A[i] 这意味着i太小， 我们必须增大它。
	3 i>0 and j < n and A[i-1]> B[ij] 这意味着i大，我们必须减小它。 


不够： i<m⟹j>0 以及i>0⟹j<n 始终成立，所以，在情况 2 和 3中，我们不需要检查j>0 或是j<n 是否成立。

	附上JAVA代码

class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }   
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            System.out.println("int i = (iMin + iMax) / 2;  "+i);
            System.out.println("int j = halfLen - i; "+j);
            
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; 
                // i is too small
                System.out.println("i is too small ");
                System.out.println("iMin = i + 1; "+iMin);
            }else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; 
                // i is too big
                System.out.println("i is too big ");
                System.out.println("iMax = i - 1; "+iMax);
            }else { 
                // i is perfect
                int maxLeft = 0;
                // 注意 这里永远是 j-1 或 i-1 因为 A[i-1] | A[i]  i-1 永远在左边  不管奇偶数
                if (i == 0) { 
                    maxLeft = B[j-1];
                    System.out.println("maxLeft = B[j-1]; "+maxLeft);
                }else if (j == 0) {
                    maxLeft = A[i-1]; 
                    System.out.println("maxLeft = A[i-1]; "+maxLeft);
                }else { 
                    maxLeft = Math.max(A[i-1], B[j-1]); 
                    System.out.println("maxLeft = Math.max(A[i-1], B[j-1]); "+maxLeft);
                }
                
                if ( (m + n) % 2 == 1 ) { 
                    // 奇数到此就已经找到了
                    System.out.println("return maxLeft; "+maxLeft);
                    return maxLeft; 
                }

                // 寻找右边
                int minRight = 0;
                if (i == m) { 
                    minRight = B[j]; 
                    System.out.println("minRight = B[j]; "+minRight);
                }else if (j == n) { 
                    minRight = A[i]; 
                    System.out.println("minRight = A[i]; "+minRight);
                }else { 
                    minRight = Math.min(B[j], A[i]); 
                    System.out.println("minRight = Math.min(B[j], A[i]); "+minRight);
                }

                System.out.println("return (maxLeft + minRight) / 2.0;"+ (maxLeft + minRight) / 2.0);
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}

public class test1
{
    public static void main(String[] args)
    {
        Solution s=new Solution();
        int  [] arr1= {1,2,4,6,7,8,9};
        int  [] arr2={-2,3,4,6,7,8,10};
        double k=s.findMedianSortedArrays(arr1,arr2);
        System.out.println("median = "+k);
    }
}




	输出结果

int i = (iMin + iMax) / 2;  3
int j = halfLen - i; 4
i is too big 
iMax = i - 1; 2
int i = (iMin + iMax) / 2;  1
int j = halfLen - i; 6
i is too small 
iMin = i + 1; 2
int i = (iMin + iMax) / 2;  2
int j = halfLen - i; 5
maxLeft = Math.max(A[i-1], B[j-1]); 7
minRight = Math.min(B[j], A[i]); 8
return (maxLeft + minRight) / 2.0;7.5
median = 7.5
