

// 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

/** 
 * Name: TANGTAO
 * fileName: .java 
 * packageName: xxxx
 * date: 2019-2-27 00:43:30
 * copyright(c) 本程序由杭州电子科技大学2016级管理学院唐涛开发,本人邮箱 : tangtao16011324@outlook.com;
 */
 
public int reverse(int x) {
        int rev=0;
        while(x!=0){
            int pop=x%10;
            x/=10;
            if(rev>Integer.MAX_VALUE/10 || (rev==Integer.MAX_VALUE/10 && pop>7) ){
                return 0;
            }else if(rev<Integer.MIN_VALUE/10 || (rev==Integer.MIN_VALUE/10 && pop<-8)){
                return 0;
            }
            rev=rev*10+pop;
        }
        
        return rev;
    }
    
    
    
