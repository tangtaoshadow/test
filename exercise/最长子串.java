给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

思路

针对字符串abcdabefd

我们可以用HashMap ，首先定义两个变量，一个变量从头移动到尾，另一个则是标记使字符串不重复的最小索引；

查看执行过程

i-->0 j-->0  >a
res-->1

i-->1 j-->0  >b
res-->2

i-->2 j-->0  >c
res-->3

i-->3 j-->0  >d
res-->4

这个时候又出现了a，所以j要移动一位，即从0到1；同时这时res不能增加，因为i移动一位，但是j移动到了出现重复字母的位置，所以res要等于 i-j+1或者之前保存的res 这两数的最大值
i-->4 j-->0  >a
j-->1
res-->4

i-->5 j-->1  >b
j-->2
res-->4

i-->6 j-->2  >e
res-->5

i-->7 j-->2  >f
res-->6

执行到结尾
i-->8 j-->2  >d
j-->4
res-->6

输出最终结果
maxlength = 6


归纳


第n次
   j              i
   |              |
   |              |
--------------------------------
   |<---  res --->|


第n+1次
   j                i+1
   |                |
   |                |
--------------------------------


这个时候如果出现重复的 那么j的取值只可能落在i与j之间,
那么我们分析这个res即不重复的字符串最大长度会等于多少呢？
j向右移动一位，这个res还可以表示i与j之间的长度，但是j只要不是移动一位，
res就只表示之前存在的一次最大的长度
依次类推
最终直到循环结束，找出最大值





JAVA代码实现

/***
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 */



import java.util.HashMap;

/**
    这是我的第一个java程序
*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
         // 首先检查输入
         if(s==null || 0==s.length()){
            // 输入有误
            return 0;
        }

        HashMap<Character,Integer> map=new HashMap<>();

        // 记录不重复字符串的最大长度
        int res=0;
        
        for(int i=0,j=0,len=s.length();i<len;i++){
            
            System.out.println();
            System.out.println("i-->"+i+" j-->"+j+"  >"+s.charAt(i));
            
            // 判断是否包含指定的键值
            if(map.containsKey(s.charAt(i))){
                
                /**
                 * 首先要弄清楚res代表的什么
                 * res 代表的是 当前记录的res值 和 i与j之间距离 这两个数其中的最大值
                 * 
                 * 其次弄清i j代表什么
                 * i 只负责从左至右依次移动
                 * j 负责标记从 i 往左移动直到使字符串不重复的最小索引
                 * 
                 * 那么现在我们来思考不重复字符串的最大的长度
                 * 一种情况就是i与j之间最大的距离
                 * 另一种情况就是res保存的值会大于i与j之间的距离
                 * 
                 */
                // 移动 j到 从 i 往左移动直到使字符串不重复的最小索引
                j=Math.max(j,map.get(s.charAt(i))+1);
                System.out.println("j-->"+j);
            }
            
            // 一次插入一个字符
            map.put(s.charAt(i),i);

            // 比较 是i与j长 还是保存的res长
            res=Math.max(res,i-j+1);

            System.out.println("res-->"+res);
        }

        return res;

    }
}



public class test1
{
    public static void main(String[] args)
    {
        Solution s=new Solution();
        int k=s.lengthOfLongestSubstring("abcdabefd");
        System.out.println("maxlength = "+k);
    }
}









