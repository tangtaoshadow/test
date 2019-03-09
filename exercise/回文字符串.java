最长回文串
2019-3-9 20:28:31

思路给定一个字符串 s，找到 s 中最长的回文子串。





对于一个字符串，例如 "123aabbaa873" 
	我们可以先从0开始，假设0就是回文字符串的正中心，根据回文字符串（中心对称）的特征，我们接下来比较的是(0,0) 和 (0,1)；我们记为(i,i+1);因为有一种情况是aa，我们发现其实中心在两个a中间。

	接下来我们再假设1是回文字符串的中心，那么最先比较的是(1,1), (1,2)，…,依次类推，我们从0一直遍历到s.length();

	我们该如何断定哪个位置对应的回文字符串是最长的呢?

如果左边的等于右边的，比如(5,6)，首先 s.charAt(5) == s.charAt(6)；那么我们再让5减少1,6增加1，判断s.charAt(4) 与 s.charAt(7)是否相等，结果相等，我们继续让左边减少1，右边增加1，直到出现(2,9)，这时s.charAt(2) !=s.charAt(9)；那么(5,6)这个位置对应的最长回文字符串是9-2-1=6，结果应该返回6,返回的值我们记为len;

接下来我们来把返回的6记录下来，用来与之前的最大的数进行比较，如果这个时候发现6大于之前保存的数，那么我们就记录6，但是我们要返回最长的回文字符串，所以我们可以记录回文字符串的开始和结束位置，由于我们只知道左边位置和对应的最长回文串的长度，并且左边位置是不是中间位置和最长回文串长度的奇偶性都不确定，我们该如何确定开始和结束位置呢?


我们先不看左边位置i是不是中间位置，我们先看最长回文串的长度len的奇偶性，
如果它是奇数，那么必然就有一个字符不是一对，而是一个，而且我们总是假设要么左边是回文字符串的中心，要么假设左边和右边的中间是回文字符串的中心，因此这时中心位置必然就是左边，那么这样我们就确定了左边开始位置start=i-(len-1)/2，结束位置end=i+len/2;因为len为奇数，对于int型变量来说，len/2必然少掉1,这就是右边字符串的个数。
如果它是偶数，说明中心位置在i和i+1的中间，同样，我们确定了左边开始位置 start=i-(len-1)/2;end=i+len/2；len/2刚好是右边的字符串个数，所以i+len/2刚好。


附上Java代码
class Solution {
    
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        
        int start = 0, end = 0;
        
        for (int i = 0; i < s.length(); i++) {
            System.out.println("");
            System.out.println("i ="+i);
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            System.out.println("len :"+len);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
                System.out.println("start :"+start);
                System.out.println("end :"+end);
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        System.out.println("String  is "+s+" left "+left+"  right "+right);
        int L = left, R = right;
        while (L >= 0 && R < s.length() && (s.charAt(L) == s.charAt(R))) {
            L--;
            R++;
            System.out.println("L "+L+" R"+R);
        }
        System.out.println("R - L - 1 :"+(R - L - 1));
        return R - L - 1;
    }
    
}


public class test1
{
    public static void main(String[] args)
    {
        Solution s=new Solution();
        String str= s.longestPalindrome("123aabbaa873");
        System.out.println("Longest palindrome string is "+str);
    }
}




运行过程


i =0
String  is 123aabbaa873 left 0  right 0
L -1 R1
R - L - 1 :1
String  is 123aabbaa873 left 0  right 1
R - L - 1 :0
len :1
start :0
end :0

i =1
String  is 123aabbaa873 left 1  right 1
L 0 R2
R - L - 1 :1
String  is 123aabbaa873 left 1  right 2
R - L - 1 :0
len :1
start :1
end :1

i =2
String  is 123aabbaa873 left 2  right 2
L 1 R3
R - L - 1 :1
String  is 123aabbaa873 left 2  right 3
R - L - 1 :0
len :1
start :2
end :2

i =3
String  is 123aabbaa873 left 3  right 3
L 2 R4
R - L - 1 :1
String  is 123aabbaa873 left 3  right 4
L 2 R5
R - L - 1 :2
len :2
start :3
end :4

i =4
String  is 123aabbaa873 left 4  right 4
L 3 R5
R - L - 1 :1
String  is 123aabbaa873 left 4  right 5
R - L - 1 :0
len :1

i =5
String  is 123aabbaa873 left 5  right 5
L 4 R6
R - L - 1 :1
String  is 123aabbaa873 left 5  right 6
L 4 R7
L 3 R8
L 2 R9
R - L - 1 :6
len :6
start :3
end :8

i =6
String  is 123aabbaa873 left 6  right 6
L 5 R7
R - L - 1 :1
String  is 123aabbaa873 left 6  right 7
R - L - 1 :0
len :1

i =7
String  is 123aabbaa873 left 7  right 7
L 6 R8
R - L - 1 :1
String  is 123aabbaa873 left 7  right 8
L 6 R9
R - L - 1 :2
len :2

i =8
String  is 123aabbaa873 left 8  right 8
L 7 R9
R - L - 1 :1
String  is 123aabbaa873 left 8  right 9
R - L - 1 :0
len :1

i =9
String  is 123aabbaa873 left 9  right 9
L 8 R10
R - L - 1 :1
String  is 123aabbaa873 left 9  right 10
R - L - 1 :0
len :1

i =10
String  is 123aabbaa873 left 10  right 10
L 9 R11
R - L - 1 :1
String  is 123aabbaa873 left 10  right 11
R - L - 1 :0
len :1

i =11
String  is 123aabbaa873 left 11  right 11
L 10 R12
R - L - 1 :1
String  is 123aabbaa873 left 11  right 12
R - L - 1 :0
len :1
Longest palindrome string is aabbaa



