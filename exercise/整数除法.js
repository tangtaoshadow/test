
给出给定两个整数int，被除数x和除数 y;计算两数相除的结果int，不能使用乘法、除法和 mod 运算符 


从案例引入：我们来分析一下19/3;令x=19,y=3;那么x/y会等于多少呢？

	首先我们先定义一个变量，一个是i=2^0;
	那么我们要得出y*i>x,即3*i>19,其中i必须等于2^n(n为整数);
n=0;3*2^0=3<19;
n=1;3*2^1=6<19;
n=2;3*2^2=12<19;
n=3;3*2^3=24>19; 满足条件
这时我们让x=x-2^n;即x=19-3*2^2=7;

	我们继续计算y*i>x,即3*i>7
n=0;3*2^0=3<7;
n=1;3*2^1=6<7;
n=2;3*2^2=12>7;  满足条件
这时我们让x=x-2^n;即x=7-3*2^1=1;

	我们继续计算y*i>x,即3*i>1
因为n=0;3*2^0=3>1;满足条件,此时1/3=0


	综合以上思路，我们得出19=1+6+12=2^0+3*2^1+3*2^2
那么19/3=2^0/3+3*2^1/3+3*2^2/3=0+2^1+2^2;

最终结果就是2^1+2^2=6;

现在来解释为什么这样做？
	因为我们有一个除数y,y<<=1就表示y=y*2;这样我们就可以求出满足y*2^n>x的最小n（令n=n1+1就是满足y*2^n>x的最小n）;那么再在这个基础上记住2^n1;将x拆分为一个x1,2^n1;即x=x1+y*2^n1;
依据以上原理，我们依次求解，最终会得出x=y*2^n1+y*2^n2+y*2^n3+……

	接下来我们来讨论余项：
如果x/y没有余数，那么上式就刚好表示完，
如果x/y有余数，那么这个式子最后余项一定是它的余数，x=y*2^n1+y*2^n2+y*2^n3+……+(x%y);
因为这里都是int型变量，所以后面一项就可以去掉



现在我们来开始写代码
我们先针对正整数除以两个正整数
divi是除数,divid是被除数;


let res = 0;
let tmp = 0;
let cnt =1;
// 如果除数大于被除数,显然这时要么是已经除尽要么有余数 但是都是0
while(divi<=divid){
				// cnt=2^0 然后开始左移
            cnt = 1;
            tmp = divi;
            // tmp 和cnt同时增长 最终会得出 divi*cnt>divid 这时tmp=divi*cnt
            while(tmp<=divid){
                tmp<<=1;
                cnt<<=1;
            }
				// 这个就是其中一项的结果  不够这里是除以2 因为循环里出来是刚好大于的
            res+=(cnt>>1);
            //减去得出结果的这一项
            divid-=(tmp>>1);
				// 再次开始循环
        }

最终得出的结果就保存在res中


完整代码

完整代码还要考虑负数的情况，我们可以把它们都转换为正数，然后用一个变量保存结果是否需要加负号。
还需要注意最小负数比最大正数绝对值要大1,除数不为0等

附上JAVA代码



public int divide(int dividend, int divisor) {
    // 除数不能为0
    if(divisor==0){
        return 0;
    }
    int max=Integer.MAX_VALUE;
    int min=Integer.MIN_VALUE;
    //处理最大最小值取模的情况。
    long divid = (long)dividend;
    long divi = (long)divisor;
    
    // 重复
    if(divi==1) {
        return (int) divid;
    }
    if(divi==-1){
        // 注意 最小负数的绝对值大于最大正数
        if(divid<=min){
            return max;
        }
        return (int)-divid;
    }
    
    boolean flag=true;
    boolean flag=true;
    if((divid<0&&divi>0)||((divid>0)&&(divi<0))){
        flag=false;
    }

    // 变成正数
    if(divid<0){
        divid=-divid;
    }
    if(divi<0){
        divi=-divi;
    }

    long res = 0;
    long tmp = 0;
    long cnt =1;
    while(divi<=divid){
        //2^0开始左移
        cnt = 1;
        tmp = divi;
        // 找到第一个大于被除数的2^n次方
        while(tmp<=divid){
            tmp<<=1;
            cnt<<=1;
        }
        res+=(cnt>>1);
        // 减去基数的前一项
        divid-=(tmp>>1);
    }
    return flag?(int)res:(int)-res;
}


附上ES6代码



   let divide=function(dividend, divisor) {
        
        // 除数不为0
        if(0==divisor){
            return 0;
        }else if(1==divisor) {
            // 减少重复运算
            return  dividend;
        }
        
        let max=2147483647;
        let min=-2147483648;
        
        // 处理最大最小值取模的情况。
        let divid = dividend;
        let divi = divisor;
        
        if(divi==-1){
            // 注意 最小负数的绝对值大于最大正数
            if(divid<=min){
                return max;
            }
            return -divid;
        }

        let flag=true;
        if((divid<0&&divi>0)||((divid>0)&&(divi<0))){
            flag=false;
        }
        // 变成正数
        if(divid<0){
            divid=-divid;
        }
        if(divi<0){
            divi=-divi;
        }
        let res = 0;
        let tmp = 0;
        let cnt =1;
        while(divi<=divid){
            consolelog('while(divi<=divid)'+'while('+divi+'<='+divid+')');
            //2^n次方
            consolelog('cnt = 1;tmp = '+divi+';');
            cnt = 1;
            tmp = divi;
            //找到第一个大于被除数的2^n次方
            consolelog('while(tmp<=divid)'+'-->while('+tmp+'<='+divid+')');
            while(tmp<=divid){
                consolelog('tmp<<=1;cnt<<=1;'+tmp+'<<=1;'+cnt+'<<=1');
                tmp<<=1;
                cnt<<=1;
                consolelog('>tmp='+tmp+';cnt='+cnt+';');
            }
            consolelog('res+=(cnt>>1);'+res+'+=('+cnt+'>>1);');
            res+=(cnt>>1);
            consolelog('>res='+res);
            //减去基数的前一个数
            consolelog('divid-=(tmp>>1);'+divid+'-=('+tmp+'>>1);');
            divid-=(tmp>>1);
            consolelog('>divid='+divid);
            consolelog();
        }
        
        return flag?res:-res;
    }
    let i=29,j=-6;
    let res=divide(i,j);
    consolelog(i+'/'+j+'='+res);



	执行结果

    
while(divi<=divid)while(4<=26)
cnt = 1;tmp = 4;
while(tmp<=divid)-->while(4<=26)
tmp<<=1;cnt<<=1;4<<=1;1<<=1
>tmp=8;cnt=2;
tmp<<=1;cnt<<=1;8<<=1;2<<=1
>tmp=16;cnt=4;
tmp<<=1;cnt<<=1;16<<=1;4<<=1
>tmp=32;cnt=8;
res+=(cnt>>1);0+=(8>>1);
>res=4
divid-=(tmp>>1);26-=(32>>1);
>divid=10


while(divi<=divid)while(4<=10)
cnt = 1;tmp = 4;
while(tmp<=divid)-->while(4<=10)
tmp<<=1;cnt<<=1;4<<=1;1<<=1
>tmp=8;cnt=2;
tmp<<=1;cnt<<=1;8<<=1;2<<=1
>tmp=16;cnt=4;
res+=(cnt>>1);4+=(4>>1);
>res=6
divid-=(tmp>>1);10-=(16>>1);
>divid=2


26/4=6








