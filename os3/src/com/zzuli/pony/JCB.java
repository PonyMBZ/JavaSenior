package com.zzuli.pony;

/**
 * @auther pony
 * @create 2022-04-20 15:44
 */
public class JCB {
    private int JCBId;
    private int JCBSize;

    public JCB() {
    }

    public JCB(int JCBId, int JCBSize) {
        this.JCBId = JCBId;
        this.JCBSize = JCBSize;
    }

    public int getJCBId() {
        return JCBId;
    }

    public void setJCBId(int JCBId) {
        this.JCBId = JCBId;
    }

    public int getJCBSize() {
        return JCBSize;
    }

    public void setJCBSize(int JCBSize) {
        this.JCBSize = JCBSize;
    }

    @Override
    public String toString() {
        return "JCB{" +
                "JCBId=" + JCBId +
                ", JCBSize=" + JCBSize +
                '}';
    }
}
