
/**
    请你来实现一个 atoi 函数，使其能将字符串转换成整数。
    
    这是我的第一个java程序
    2019-3-15 20:11:50
    tangtao  杭州电子科技大学
    tangtao16011324@outlook.com
*/


class Solution {
    public int myAtoi(String str) {
		str=str.trim();
		System.out.println("str "+str);
        if(null==str||0==str.length()){
			return 0;
		}
		
		char firstChar=str.charAt(0);
		int sign=1,start=0;
		long res=0,max=10000000000L;
		
		// 判断符号
		if('+'==firstChar){
			sign=1;
			start++;
		}else if('-'==firstChar){
			sign=-1;
			start++;
		}

		// 加快速度
		for(int i=start,len=str.length();i<len;i++){
			if(!Character.isDigit(str.charAt(i))){
				break;
			}
			res=res*10+str.charAt(i)-'0';
			//  用来防止数过大 导致long型装不下 而出现错误  并且加快执行速度
			if(max<res) {
				break;
			}
			System.out.println("res: "+res);
		}
		
		// 判断是否超出int 这里要注意max_value 的绝对值要比 min_value 小1 所以要分开判断  
		if(1==sign && Integer.MAX_VALUE<res){
			return (int)  Integer.MAX_VALUE;
		}else if(-1==sign && Integer.MIN_VALUE> -res){
			return (int)  Integer.MIN_VALUE;
		}
					
					
		System.out.println("res "+res+"  sign "+sign);
		return (int) res*sign;


    }
}





public class test1
{
    public static void main(String[] args)
    {
    	Solution s=new Solution();
    	int res= s.myAtoi("9223");
    	System.out.println("res is "+res);
    }
}

