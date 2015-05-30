package com.mrmenezes.novoinicio;
import android.util.Log;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.text.Text;
import org.andengine.extension.collisions.entity.sprite.PixelPerfectAnimatedSprite;
import org.andengine.extension.collisions.opengl.texture.region.PixelPerfectTiledTextureRegion;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import java.util.Vector;

/**
 * Created by Mrmenezes on 09/04/2015.
 */
public class Map {

    private Vector<PixelPerfectAnimatedSprite> nSpriteTail,nSprite;
    private int[] indext;
    private int[][] mat;
    private Text textCollision;
    private Vector<Text> textTail;




    public Map(Font mFont, int dificuldade_,Scene mScene,int[] index,int[][] matriz, PixelPerfectTiledTextureRegion pTiledTextureRegion1,PixelPerfectTiledTextureRegion pTiledTextureRegion2, VertexBufferObjectManager pVertexBufferObjectManager) {


        this.mat = matriz;



        nSpriteTail = new Vector<PixelPerfectAnimatedSprite>();
        int cont = 0;
        for (int j = 0;j<6;j++) {
            for (int i = 0; i < 6; i++) {
            if(this.mat[j][i] == 1){
                //mSpriteTail[cont] = new AnimatedSprite(80 + (64 * i), 80 + (64 * j), pTiledTextureRegion, pVertexBufferObjectManager);

                nSpriteTail.addElement(new PixelPerfectAnimatedSprite(240+(64 * j)-48, 512 - (64 * i) , pTiledTextureRegion1, pVertexBufferObjectManager));
                mScene.attachChild(nSpriteTail.get(cont));
                nSpriteTail.get(cont++).setCurrentTileIndex(1);
                             //final PhysicsHandler physicsHandler = new PhysicsHandler(mSpriteTail[cont]);
                    //mSpriteTail[cont].registerUpdateHandler(physicsHandler);
              }
            }
        }



       nSprite   = new Vector<PixelPerfectAnimatedSprite>();
        for (int s = 0;s<index.length;s++) {
            // mSprite[s] = new Sprits(this,index[s], mScene, 80 + (80 * s), 600 , pTiledTextureRegion, pVertexBufferObjectManager);
            if(index[s]>63)
                nSprite.addElement( new  Sprits(dificuldade_,this,index[s], mScene, 240+(96*s)-48, 128 , pTiledTextureRegion2, pVertexBufferObjectManager));
            else
                nSprite.addElement( new  Sprits(dificuldade_,this,index[s], mScene, 240+(96*s)-48, 128 , pTiledTextureRegion1, pVertexBufferObjectManager));

        }


        //this.nSprite = mSprite;
        //this.nSpriteTail = mSpriteTail;
        this.indext = index;
        textCollision = new Text(240, 0, mFont, "Perdendo",pVertexBufferObjectManager);
        mScene.attachChild(textCollision);
        final  Vector<Text>  idtextTail = new Vector<Text>();
        for (int a = 0;a<nSpriteTail.indexOf(nSpriteTail.lastElement())+1;a++) {
            idtextTail.addElement(new Text(nSpriteTail.get(a).getX()+96 , nSpriteTail.get(a).getY()+96 , mFont, "" + a, pVertexBufferObjectManager));
            idtextTail.get(a).setScale(.5f);
            mScene.attachChild(idtextTail.get(a));
        }

        this.textTail = idtextTail;
       /* mScene.registerUpdateHandler(new IUpdateHandler() {
            @Override
            public void reset() { }

            @Override
            public void onUpdate(final float pSecondsElapsed) {
            for (int a = 0; a < nSpriteTail.indexOf(nSpriteTail.lastElement()) + 1; a++) {
                    textTail.get(a).setColor(Color.RED);
                    for (int b = 0; b < nSprite.indexOf(nSprite.lastElement()) + 1; b++) {
                        if(nSpriteTail.get(a).collidesWith(nSprite.get(b))){
                            textTail.get(a).setColor(Color.CYAN);

                        }
                    }
                }


            }



        });*/



    }

    public void upClick(){
        for (int a = 0; a < nSpriteTail.indexOf(nSpriteTail.lastElement()) + 1; a++) {
            textTail.get(a).setColor(Color.RED);
            for (int b = 0; b < nSprite.indexOf(nSprite.lastElement()) + 1; b++) {
                if(nSpriteTail.get(a).collidesWith(nSprite.get(b))){
                    textTail.get(a).setColor(Color.CYAN);

                }
            }
        }

    if( textTail.get(13).getColor()==Color.CYAN){
        textCollision.setText("Ganhou");
    }
    }



    public void Movendo(PixelPerfectAnimatedSprite SpritMove,boolean movendo){

        for (int a = 0; a < nSprite.indexOf(nSprite.lastElement()) + 1; a++) {

            if(movendo) {

                //Log.e(" Desativando ",""+((Sprits) this.nSprite[i]).indexAnimate);
                if (this.nSprite.get(a) != SpritMove)
                    ((Sprits) this.nSprite.get(a)).MovendoOutros(true);

            }else{
                //Log.e(" Ativando",""+((Sprits) this.nSprite[i]).indexAnimate);
                if (this.nSprite.get(a) != SpritMove)
                    ((Sprits) this.nSprite.get(a)).MovendoOutros(false);
            }

        }

    }






}
