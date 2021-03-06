package com.trainer.math.mathtrainer;

public class Equation {
    private int x;
    private int y;
    private int result;

    public Equation(int x, int y) {
        this.x = x;
        this.y = y;
        this.result = x * y;
    }

    public int getX() {
        return x;
    }

    private void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    private void setY(int y) {
        this.y = y;
    }

    public int getResult() {
        return result;
    }

    public String getStrResult(){
        return Integer.toString(getResult());
    }

    public int multiply() {
        setResult(getX() * getY());
        return result;
    }

    private void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Equation{" +
                "x=" + x +
                ", y=" + y +
                ", result=" + result +
                '}';
    }
}
