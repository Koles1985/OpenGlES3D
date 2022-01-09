package com.koles.opengles3d;

import android.opengl.GLU;

import com.koles.angin.GLGame;
import com.koles.angin.GLScreen;
import com.koles.graphic.Vertices3;

import javax.microedition.khronos.opengles.GL10;

public class Vertices3Screen extends GLScreen {
    Vertices3 vertices;

    public Vertices3Screen(GLGame game){
        super(game);

        vertices = new Vertices3(glGraphics, 6, 0, true,
                false, false );
        vertices.setVertices(new float[]{
                -0.5f, -0.5f, -3, 1, 0, 0, 0.5f,
                0.5f, -0.5f, -3, 1, 0, 0, 0.5f,
                0.0f, 0.5f, -3, 1, 0, 0, 0.5f,

                0.0f, -0.5f, -5, 0, 0, 1, 1,
                1.0f, -0.5f, -5, 0, 0, 1, 1,
                0.5f, 0.5f, -5, 0, 0, 1, 1
        }, 0, 7 * 6);

    }

    @Override
    public void resume() {

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void present(float deltaTime) {
        GL10 gl = glGraphics.getGl10();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        //камера проэкции
        GLU.gluPerspective(gl, 67, glGraphics.getWidth() /
                        (float)glGraphics.getHeight(), 0.1f, 10f);
        //gl.glOrthof(-1, 1, -1, 1, 10, -10);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        // проверка z буфера
        gl.glEnable(GL10.GL_DEPTH_TEST);
        // альфа смешивание
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);


        vertices.bind();
        // 1 - рисует все треугольники
        //2 - рисует указанные треугольники в указанной последовательности
        //1 vertices.draw(GL10.GL_TRIANGLES, 0, 6);
        //2
        vertices.draw(GL10.GL_TRIANGLES, 3, 3);// красный треугольник альфа = 0.5
        vertices.draw(GL10.GL_TRIANGLES, 0, 3);//синий треугольник альфа = 1
        vertices.unbind();

        gl.glDisable(GL10.GL_BLEND);
        gl.glDisable(GL10.GL_DEPTH_TEST);
    }

    @Override
    public void pause() {

    }

    @Override
    public void dispose() {

    }
}
