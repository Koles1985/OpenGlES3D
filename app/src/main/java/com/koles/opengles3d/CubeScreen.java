package com.koles.opengles3d;

import android.opengl.GLU;

import com.koles.angin.GLScreen;
import com.koles.angin.Game;
import com.koles.graphic.Texture;
import com.koles.graphic.Vertices3;

import javax.microedition.khronos.opengles.GL10;

public class CubeScreen extends GLScreen {
    Vertices3 cube;
    Texture texture;
    float angle = 0;

    public CubeScreen(Game game) {
        super(game);
        cube = createCube();
        texture = new Texture(glGame, "got.png");
    }

    private Vertices3 createCube(){
        float[] vertices = {
                -0.5f, -0.5f,  0.5f, 0, 1,
                 0.5f, -0.5f,  0.5f, 1, 1,
                 0.5f,  0.5f,  0.5f, 1, 0,
                -0.5f,  0.5f,  0.5f, 0, 0,

                 0.5f, -0.5f,  0.5f, 0, 1,
                 0.5f, -0.5f, -0.5f, 1, 1,
                 0.5f,  0.5f, -0.5f, 1, 0,
                 0.5f,  0.5f,  0.5f, 0, 0,

                 0.5f, -0.5f, -0.5f, 0, 1,
                -0.5f, -0.5f, -0.5f, 1, 1,
                -0.5f,  0.5f, -0.5f, 1, 0,
                 0.5f,  0.5f, -0.5f, 0, 0,

                -0.5f, -0.5f, -0.5f, 0, 1,
                -0.5f, -0.5f,  0.5f, 1, 1,
                -0.5f,  0.5f,  0.5f, 1, 0,
                -0.5f,  0.5f, -0.5f, 0, 0,

                -0.5f,  0.5f,  0.5f, 0, 1,
                 0.5f,  0.5f,  0.5f, 1, 1,
                 0.5f,  0.5f, -0.5f, 1, 0,
                -0.5f,  0.5f, -0.5f, 0, 0,

                -0.5f, -0.5f,  0.5f, 0, 1,
                 0.5f, -0.5f,  0.5f, 1, 1,
                 0.5f, -0.5f, -0.5f, 1, 0,
                -0.5f, -0.5f, -0.5f, 0, 0
        };

        short[] indices = {
                0, 1, 3,
                1, 2, 3,
                4, 5, 7,
                5, 6, 7,
                8, 9, 11,
                9, 10, 11,
                12, 13, 15,
                13, 14, 15,
                16, 17, 19,
                17, 18, 19,
                20, 21, 23,
                21, 22, 23
        };

        Vertices3 cube = new Vertices3(glGraphics, 24, 36,
                false, true, false);

        cube.setVertices(vertices, 0, vertices.length);
        cube.setIndices(indices, 0, indices.length);
        return  cube;
    }

    @Override
    public void resume() {
        texture.reload();
    }

    @Override
    public void update(float deltaTime) {
        angle += 45 * deltaTime;
    }

    @Override
    public void present(float deltaTime) {
        GL10 gl = glGraphics.getGl10();
        gl.glViewport(0,0, glGraphics.getWidth(), glGraphics.getHeight());
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 67, glGraphics.getWidth() /
                (float)glGraphics.getHeight(), 0.1f, 10.0f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        texture.bind();
        cube.bind();
        gl.glTranslatef(0, 0, -3);
        gl.glRotatef(angle, 1, 1, 0);
        cube.draw(GL10.GL_TRIANGLES, 0, 36);
        cube.unbind();
        gl.glDisable(GL10.GL_DEPTH_TEST);
        gl.glDisable(GL10.GL_TEXTURE_2D);
    }

    @Override
    public void pause() {

    }

    @Override
    public void dispose() {

    }
}