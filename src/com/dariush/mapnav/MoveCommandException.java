package com.dariush.mapnav;

import java.io.IOException;

public class MoveCommandException  extends IOException {
    public MoveCommandException(String errorMessage) {
        super(errorMessage);
    }
}
