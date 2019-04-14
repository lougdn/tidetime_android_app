package com.example.project;

import android.app.Activity;
import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;

public class RegistrationActivity {
    FileOutputStream outputStream;

    try {
        outputStream = Context.openFileOutput("log", Context.MODE_PRIVATE);

    } catch (Exception e) {

    }
}
