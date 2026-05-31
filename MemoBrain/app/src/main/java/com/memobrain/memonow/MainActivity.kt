package com.memobrain.memonow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.memobrain.memonow.features.cadernos.CadernosTelas
//import com.memobrain.memonow.features.cadernos.CadernosTelas2
//import com.memobrain.memonow.features.login.LoginTela
import com.memobrain.memonow.ui.tema.MemonowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Descomente apenas a tela que você quer testar no momento:
            MemonowTheme() {
                //LoginTela()
                CadernosTelas()
                //CadernosTelas2()
            }
        }
    }
}
