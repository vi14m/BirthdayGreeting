package com.vishal_learning.birthdaygreeting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.vishal_learning.birthdaygreeting.ui.theme.Blue500
import com.vishal_learning.birthdaygreeting.ui.theme.Orange500
import com.vishal_learning.birthdaygreeting.ui.theme.Red500

@Composable
fun HomeScreen(){
    Box {
        lottieAnimation(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            Id=R.raw.celebration
        )
        lottieAnimation(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .offset(y = -180.dp),
            Id=R.raw.celebration
        )
        lottieAnimation(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .offset(y = 260.dp),
            Id=R.raw.celebration
        )
        lottieAnimation(
            modifier = Modifier
                .fillMaxSize(),
            Id=R.raw.clebration2
        )
    }
    LottieAnimationWithCard()

}


@Composable
fun lottieAnimation(modifier: Modifier = Modifier,Id:Int){
    val loaderLottieComposition1 by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            Id
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        loaderLottieComposition1,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )


    LottieAnimation(
        composition = loaderLottieComposition1,
        progress = preloaderProgress,
        modifier = modifier
    )
}

@Composable
fun LottieAnimationWithCard() {
    var isCardVisible by remember { mutableStateOf(false) }
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.birthdaycake)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    isCardVisible = !isCardVisible
                },
            verticalArrangement = Arrangement.Bottom
        ) {
            if (isCardVisible) {
                BirthdayCard()
            }
            LottieAnimation(
                composition = composition,
                progress = progress,
                modifier = Modifier
            )
        }
    }
}


@Composable
fun BirthdayCard() {
    val horizontalGradientBrush = Brush.horizontalGradient(
        colors = listOf(Blue500, Red500, Orange500)
    )
    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .padding(24.dp)
                .offset(y = (-18).dp),
            elevation = CardDefaults.cardElevation(
                10.dp,
                hoveredElevation = 12.dp
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Box(
                Modifier
                    .background(brush = horizontalGradientBrush)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Happy Birthday!",
                        style = TextStyle(fontSize = 26.sp,color= Color.Black),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    Text(
                        text = "Wishing you a wonderful day filled with joy and happiness.",
                        style = TextStyle(fontSize = 20.sp,color= Color.White),
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    Text(
                        text = "~Vishal", textAlign = TextAlign.End,
                        color= Color.Black,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}