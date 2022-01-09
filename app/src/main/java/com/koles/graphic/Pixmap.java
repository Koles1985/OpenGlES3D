package com.koles.graphic;

import com.koles.graphic.Graphics.PixmapFormat;

public interface Pixmap {
    int getWidth();
    int getHeight();
    Graphics.PixmapFormat getFormat();
    void dispose();
}
