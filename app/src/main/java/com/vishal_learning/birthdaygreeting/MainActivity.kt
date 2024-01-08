package com.vishal_learning.birthdaygreeting

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vishal_learning.birthdaygreeting.ui.theme.BirthdayGreetingTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BirthdayGreetingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                    mediaPlayer = MediaPlayer()
                    val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.happy_birthday_file)
                    mediaPlayer.setDataSource(this@MainActivity,uri)
                    mediaPlayer.prepare()
                    val delayMillis = 800L
                    LaunchedEffect(key1 = true) {
                       lifecycleScope.launch() {
                            delay(delayMillis)
                            mediaPlayer.isLooping = true
                            // Start the audio playback
                            mediaPlayer.start()
                        }
                    }

                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMediaPlayer()
    }
    private fun releaseMediaPlayer() {
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.release()
        }
    }
}


@Composable
fun Navigation(){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen"){
        composable("splash_screen"){
            SplashScreen(navController)
        }
        composable("home_screen"){
            HomeScreen()
        }
    }
}

