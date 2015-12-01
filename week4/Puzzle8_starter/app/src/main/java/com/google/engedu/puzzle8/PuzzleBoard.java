package com.google.engedu.puzzle8;

import android.graphics.Bitmap;
import android.graphics.*;

import java.util.*;

import android.view.*;
import android.widget.*;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.provider.MediaStore;
import android.widget.*;
public class PuzzleBoard {
    ArrayList<Bitmap> chunkedImage;
ImageView mimage;
    Canvas canvas;
    private static final int NUM_TILES = 4;
    private static final int[][] NEIGHBOUR_COORDS = {
            { -1, 0 },
            { 1, 0 },
            { 0, -1 },
            { 0, 1 }
    };
    private ArrayList<PuzzleTile> tiles;

    PuzzleBoard(Bitmap bitmap, int parentWidth) {

            Bitmap bits = createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(),true);
         int width=bitmap.getWidth()/4;
        int height=bitmap.getHeight()/4;
            PuzzleTile tile1=new PuzzleTile(Bitmap.createBitmap(bits, 0, 0, width, height),0);
            tiles.add(tile1);
        tile1=new PuzzleTile(Bitmap.createBitmap(bits, width, 0, width, height),1);
        tiles.add(tile1);
       tile1=new PuzzleTile(Bitmap.createBitmap(bits, 2*width, 0, width, height),2);
        tiles.add(tile1);
         tile1=new PuzzleTile(Bitmap.createBitmap(bits, 3*width, 0, width, height),3);
        tiles.add(tile1);
        tile1=new PuzzleTile(Bitmap.createBitmap(bits,0,width, width, height),4);
        tiles.add(tile1);
         tile1=new PuzzleTile(Bitmap.createBitmap(bits,width , width, width, height),5);
        tiles.add(tile1);
        tile1=new PuzzleTile(Bitmap.createBitmap(bits,2*width , width,width, height),6);
        tiles.add(tile1);
         tile1=new PuzzleTile(Bitmap.createBitmap(bits, 3*width, width, width, height),7);
        tiles.add(tile1);
        tile1=new PuzzleTile(Bitmap.createBitmap(bits,0, 2*width, width, height),8);
        tiles.add(tile1);
        tile1=new PuzzleTile(Bitmap.createBitmap(bits,width, 2*width,width, height),9);
        tiles.add(tile1);
        tile1=new PuzzleTile(Bitmap.createBitmap(bits, 2*width, 2*width, width, height),10);
        tiles.add(tile1);
        tile1=new PuzzleTile(Bitmap.createBitmap(bits,3*width, 2*width, width, height),11);
        tiles.add(tile1);
        tile1=new PuzzleTile(Bitmap.createBitmap(bits, 0, 3*width, width, height),12);
        tiles.add(tile1);
        tile1=new PuzzleTile(Bitmap.createBitmap(bits, width, 3*width, width, height),13);
        tiles.add(tile1);
        tile1=new PuzzleTile(Bitmap.createBitmap(bits, 2*width, 3*width, width, height),14);
        tiles.add(tile1);
        canvas=new Canvas();
        draw(canvas);
    }
    PuzzleBoard(PuzzleBoard otherBoard) {
        tiles = (ArrayList<PuzzleTile>) otherBoard.tiles.clone();
    }

    public void reset() {
        // Nothing for now but you may have things to reset once you implement the solver.
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        return tiles.equals(((PuzzleBoard) o).tiles);
    }

    public void draw(Canvas canvas) {
        if (tiles == null) {
            return;
        }
        for (int i = 0; i < NUM_TILES * NUM_TILES; i++) {
            PuzzleTile tile = tiles.get(i);
            if (tile != null) {
                tile.draw(canvas, i % NUM_TILES, i / NUM_TILES);
            }
        }
    }

    public boolean click(float x, float y) {
        for (int i = 0; i < NUM_TILES * NUM_TILES; i++) {
            PuzzleTile tile = tiles.get(i);
            if (tile != null) {
                if (tile.isClicked(x, y, i % NUM_TILES, i / NUM_TILES)) {
                    return tryMoving(i % NUM_TILES, i / NUM_TILES);
                }
            }
        }
        return false;
    }

    public boolean resolved() {
        for (int i = 0; i < NUM_TILES * NUM_TILES - 1; i++) {
            PuzzleTile tile = tiles.get(i);
            if (tile == null || tile.getNumber() != i)
                return false;
        }
        return true;
    }

    private int XYtoIndex(int x, int y) {
        return x + y * NUM_TILES;
    }

    protected void swapTiles(int i, int j) {
        PuzzleTile temp = tiles.get(i);
        tiles.set(i, tiles.get(j));
        tiles.set(j, temp);
    }

    private boolean tryMoving(int tileX, int tileY) {
        for (int[] delta : NEIGHBOUR_COORDS) {
            int nullX = tileX + delta[0];
            int nullY = tileY + delta[1];
            if (nullX >= 0 && nullX < NUM_TILES && nullY >= 0 && nullY < NUM_TILES &&
                    tiles.get(XYtoIndex(nullX, nullY)) == null) {
                swapTiles(XYtoIndex(nullX, nullY), XYtoIndex(tileX, tileY));
                return true;
            }

        }
        return false;
    }

    public ArrayList<PuzzleBoard> neighbours() {
        return null;
    }

    public int priority() {
        return 0;
    }

}
