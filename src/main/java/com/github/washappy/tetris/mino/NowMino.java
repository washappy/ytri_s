package com.github.washappy.tetris.mino;

import com.github.washappy.enums.Rotates;

import java.util.Arrays;

public class NowMino extends AbstactMino{

    private int roation = 0;

    public int getRoation() {
        return roation;
    }

    public int rotate(Rotates roationType) {
        switch (roationType) {
            case CLOCKWISE -> {
                if (roation>0) {
                    roation-=1;
                } else {
                    roation = 3;
                }
            }
            case COUNTERCLOCKWISE -> {
                if (roation<3) {
                    roation+=1;
                } else {
                    roation = 0;
                }
            }
            case HUNDREDWEIGHT -> {
                if (roation<2) {
                    roation+=2;
                } else {
                    roation-=2;
                }
            }
        }
        return roation;
    }

    public NowMino(Minos mino) {
        this.mino = mino;
        this.x = AbstactMino.START_X;
        this.y = AbstactMino.START_Y;
    }

    public NowMino(Minos mino,int x, int y) {
        this.mino = mino;
        this.x = x;
        this.y = y;
    }

    public NowMino(Minos mino,int x, int y, int roation) {
        this.mino = mino;
        this.x = x;
        this.y = y;
        this.roation = roation;
    }

    public NowMino getMoved(Move move) {
        int[] m = move.getXY();
        //System.out.println(Arrays.toString(m));
        return new NowMino(mino,x+m[0],y+m[1],roation);
    }

    public void move(Move move) {
        int[] m = move.getXY();
        this.x += m[0];
        this.y += m[1];
    }

    public NowMino getRotated(Rotates r) {
        NowMino n = new NowMino(mino,x,y);
        n.roation = n.rotate(r);
        return n;
    }

    public int[][] getCoodinates() {

        int[][] ret = new int[4][2];
        int k = 0;

        int p = 0;
        for (int[] i: mino.getRotation()[roation]) {
            int q = 0;
            for (int j : i) {
                if (j==1) {
                    ret[k] = new int[]{x+q, y+p};
                    k+=1;
                }
                q+=1;
            }
            p+=1;
        }
        return ret;
    }
}
