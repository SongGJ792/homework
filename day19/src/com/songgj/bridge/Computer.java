package com.songgj.bridge;

public abstract class Computer {
    protected Brand brand;

    public Computer(Brand brand) {
        this.brand = brand;
    }

    public void sale() {
        brand.sale();
    }

    static class Laptop extends Computer{

        public Laptop(Brand brand) {
            super(brand);
        }

        @Override
        public void sale() {
            super.sale();
            System.out.println("笔记本");
        }
    }
    static class Desktop extends Computer{

        public Desktop(Brand brand) {
            super(brand);
        }

        @Override
        public void sale() {
            super.sale();
            System.out.println("台式机");
        }
    }
}
