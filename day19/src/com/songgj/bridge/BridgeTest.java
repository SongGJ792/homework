package com.songgj.bridge;

public class BridgeTest {
    public static void main(String[] args) {

//        桥接模式
//        假定有如下产品关系
//        一、电脑
//          (1)联想
//              ①台式机
//              ②笔记本
//          (2)戴尔
//              ①台式机
//              ②笔记本
//
//        显然，我们可以运用我们熟悉的继承关系来体现该产品关系，但是，如此一来，如果我们需要改变其中任一项内容的话，
//        我们就需要对整个继承体系进行改变，所以，为优化和避免这个不足之处，我们引入了桥接模式，就是对不同的变化量创建不同的类，
//        并通过某种连接实现各变量的统一。

        Computer.Desktop dd = new Computer.Desktop(new dell());
        dd.sale();

        Computer.Laptop ll = new Computer.Laptop(new lenovo());
        ll.sale();
    }
}
