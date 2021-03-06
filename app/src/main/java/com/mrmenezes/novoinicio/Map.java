package com.mrmenezes.novoinicio;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.text.Text;
import org.andengine.extension.collisions.entity.sprite.PixelPerfectAnimatedSprite;
import org.andengine.extension.collisions.opengl.texture.region.PixelPerfectTiledTextureRegion;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.Vector;

/**
 * Created by Mrmenezes on 09/04/2015.
 */
public class Map {

    private Vector<PixelPerfectAnimatedSprite> nSpriteTail, nSprite;
    private int[] indext;
    private int[][] mat;
    private Text textCollision;
    private boolean ganhou = false;


    public Map(Font mFont, int dificuldade_, Scene mScene, int[] index, int[][] matriz, PixelPerfectTiledTextureRegion pTiledTextureRegion1, PixelPerfectTiledTextureRegion pTiledTextureRegion2, VertexBufferObjectManager pVertexBufferObjectManager) {


        this.mat = matriz;


        nSpriteTail = new Vector<PixelPerfectAnimatedSprite>();
        int cont = 0;
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 6; i++) {
                if (this.mat[j][i] == 1) {

                    nSpriteTail.addElement(new PixelPerfectAnimatedSprite(240 + (64 * j) - 48, 512 - (64 * i), pTiledTextureRegion1, pVertexBufferObjectManager));
                    mScene.attachChild(nSpriteTail.get(cont));
                    nSpriteTail.get(cont++).setCurrentTileIndex(1);
                }
            }
        }


        nSprite = new Vector<PixelPerfectAnimatedSprite>();
        for (int s = 0; s < index.length; s++) {
            if (index[s] > 63) {
                nSprite.addElement(new Sprits(nSpriteTail, dificuldade_, this, index[s], mScene, 240 + (96 * s) - 48, 128, pTiledTextureRegion2, pVertexBufferObjectManager));
            } else
                nSprite.addElement(new Sprits(nSpriteTail, dificuldade_, this, index[s], mScene, 240 + (96 * s) - 48, 128, pTiledTextureRegion1, pVertexBufferObjectManager));

        }


        this.indext = index;


    }

    public boolean getGanhou() {
        return ganhou;
    }

    public void upClick() {


        this.ganhou = false;
        for (int b = 0; b < nSprite.indexOf(nSprite.lastElement()) + 1; b++) {
            if (!((Sprits) this.nSprite.get(b)).colidTotal) return;
        }

        this.ganhou = true;


    }


    public void Movendo(PixelPerfectAnimatedSprite SpritMove, boolean movendo) {

        for (int a = 0; a < nSprite.indexOf(nSprite.lastElement()) + 1; a++) {

            if (movendo) {

                //Log.e(" Desativando ",""+((Sprits) this.nSprite[i]).indexAnimate);
                if (this.nSprite.get(a) != SpritMove)
                    ((Sprits) this.nSprite.get(a)).MovendoOutros(true);

            } else {
                //Log.e(" Ativando",""+((Sprits) this.nSprite[i]).indexAnimate);
                if (this.nSprite.get(a) != SpritMove)
                    ((Sprits) this.nSprite.get(a)).MovendoOutros(false);
            }

        }

    }


}
