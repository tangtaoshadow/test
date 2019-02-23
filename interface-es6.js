
function consolelog(str=''){
    console.log(str+'\r\n');
}



// var CompositeImpl=function (){
//     this.implementsInterfaces=['Composite','FormItem'];
// }


// CompositeImpl.prototype.add=function(){
//     consolelog('add');
// }


// CompositeImpl.prototype.remove=function(){
    
// }

// CompositeImpl.prototype.update=function(){
    
// }

// CompositeImpl.prototype.select=function(){
    
// }


// // 检测CompositeImpl
// function CheckCompositeImpl(instance){
//     // 判断当前对象是否实现了所有的接口
//     if(!isImplements(instance,'Composite','FormItem')){
//         throw new Error('Object does not implement a required interface .');
//     }


// }


// // 核心方法  返回值 boolean
// function isImplements(object){
//     // arguments
//     for (var i= 1 ;i<arguments.length;i++){
        
//         var interfaceName=arguments[i];
        
//         // 判断此方法是否成功
//         var interfaceFound=false;

//         for(j=0;j<object.implementsInterfaces.length;j++){
//             if(object.implementsInterfaces[j]==interfaceName){
//                 interfaceFound = true;
//                 break;
//             }
//         }

//         if(!interfaceFound){
//             return false;
//         }

//     }

//     return true;

// }


// var c1=new CompositeImpl();
// CheckCompositeImpl(c1);
// c1.add();




// 鸭式辨型法  一个类实现接口的主要目的 把接口里的方法都实现
// 完全面向对象 代码统一 

// 1 接口类  class  interface
/***
接口的名字
接收方法名称的集合


***/

var Interface = function(name,methods){
    if(arguments.length!=2){
        throw new Error('You need to implement two methods of instantiating this interface.');
    }

    this.name=name;
    this.methods=[];
    for(var i=0,len=methods.length;i<len;i++){
        if(typeof methods[i]!=='string'){
            throw new Error('The interface methods name is error .');
        }
        this.methods.push(methods[i]);
    }


}


// 实例化接口对象
var CompositeInterface = new Interface ('CompositeInterface',['add','remove']);
var FormItemInterface = new Interface('FormItemInterface',['update','select']);

// 实例化类
var CompositeImpl=function(){

}


// 实例化接口
CompositeImpl.prototype.add=function(){
    consolelog('add');
}


CompositeImpl.prototype.remove=function(){
    consolelog('remove');
}

CompositeImpl.prototype.update=function(){
    consolelog('update');
}

CompositeImpl.prototype.select=function(){
    consolelog('select');
}


// 检验接口里的方法
Interface.ensureImplements= function(object){
    if(arguments.length < 2){
        throw new Error('Interface.ensureImplements constructor arguments must be greater than 2 ');
    }

    for(var i=1,len=arguments.length;i<len;i++){
        var instanceInterface=arguments[i];
        // 判断参数是否是接口类的类型
        if(instanceInterface.constructor!==Interface){
            throw new Error('The arguments constructor not be interface class');
        }
        // 循环接口实例对象里的每一个方法
        for(var j=0;j<instanceInterface.methods.length;j++){

            var methodName = instanceInterface.methods[j];
            if(!object[methodName] || typeof object[methodName] !== 'function'){
                throw new Error ('The method name \' '+ methodName +' \' is not found');
            }

        }

    }




}




var c1=new CompositeImpl();

Interface.ensureImplements(c1,CompositeInterface,FormItemInterface);

c1.add();







