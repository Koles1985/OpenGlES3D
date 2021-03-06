package com.koles.opengles3d;

import com.koles.graphic.Vertices3;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class HierarchicalObject {
    public float x, y, z;
    public float scale = 1;
    public float rotationY, rotationParent;
    public boolean hasParent;
    public final List<HierarchicalObject> children =
            new ArrayList<HierarchicalObject>();
    public final Vertices3 mesh;

    public HierarchicalObject(Vertices3 mesh, boolean hasParent){
        this.mesh = mesh;
        this.hasParent = hasParent;
    }

    public void update(float delaTime){
        rotationY += 45 * delaTime;
        rotationParent += 20 * delaTime;
        int len = children.size();
        for(int i = 0; i < len; i++){
            children.get(i).update(delaTime);
        }
    }

    public void render(GL10 gl){
        gl.glPushMatrix();
        if(hasParent){
            gl.glRotatef(rotationParent, 0, 1, 0);
        }
        gl.glTranslatef(x, y, z);
        gl.glPushMatrix();
        gl.glRotatef(rotationY, 0, 1, 0);
        gl.glScalef(scale, scale, scale);
        mesh.draw(GL10.GL_TRIANGLES, 0, 36);
        gl.glPopMatrix();
        int len = children.size();
        for(int i = 0; i < len; i++){
            children.get(i).render(gl);
        }
        gl.glPopMatrix();
    }
}
