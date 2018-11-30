package com.songgj.bridge;

public interface Brand {
    void sale ();
}

class dell implements Brand{

    @Override
    public void sale() {
        System.out.print("销售戴尔");
    }
}

class lenovo implements Brand{

    @Override
    public void sale() {
        System.out.print("销售联想");
    }
}