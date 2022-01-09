package com.koles.opengles3d;

import com.koles.angin.GLGame;
import com.koles.angin.Screen;

public class Launcher extends GLGame {

    @Override
    public Screen getStartScreen() {
        return new LightScreen(this);
    }
}
