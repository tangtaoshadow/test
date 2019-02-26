/***
给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

***/

/** 
 * Name: TANGTAO
 * fileName: .java 
 * packageName: xxxx
 * date: 2019-2-27 00:46:24
 * copyright(c) 本程序由杭州电子科技大学2016级管理学院唐涛开发,本人邮箱 : tangtao16011324@outlook.com;
 */
 
/****
思路
当我们遇到 nums[i] = val 时，我们可以将当前元素与最后一个元素进行交换，并释放最后一个元素。这实际上使数组的大小减少了 1。
*****/


 public int removeElement(int[] nums, int val) {
        int i=0;
        int n=nums.length;
        while(i<n){
            if(nums[i]==val){
                nums[i]=nums[n-1];
                n--;
            }else{
                i++;
            }
        }
        return n;
    }
 
 

