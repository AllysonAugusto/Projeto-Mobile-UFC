package com.memobrain.memonow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.memobrain.memonow.features.login.LoginTela
import com.memobrain.memonow.ui.tema.MemonowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemonowTheme() {
                LoginTela()
            }
        }
    }
}
