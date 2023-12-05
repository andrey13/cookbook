package com.example.cookbook.presentation.composables

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cookbook.R
import com.example.cookbook.presentation.NavRoutes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ScrSplash(nc: NavController?) {

    val scale = remember { Animatable(0f) }
    val angle = remember { Animatable(0f) }
    val offsetX = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp / 2 - 32.dp
    val screenHeight = configuration.screenHeightDp.dp / 2 - 32.dp


    LaunchedEffect(key1 = true) {
        val job1 = launch {
            launch {
                scale.animateTo(
                    targetValue = 2.0f,
                    animationSpec = tween(
                        durationMillis = 2500,
                        easing = {
                            OvershootInterpolator(2f).getInterpolation((it))
                        }
                    )
                )
            }

            launch {
                angle.animateTo(
                    targetValue = 360f,
                    animationSpec = tween(
                        durationMillis = 2500,
                        easing = {
                            OvershootInterpolator(1f).getInterpolation((it))
                        }
                    )
                )
            }
        }

        job1.join()

        delay(500L)


        val job2 = launch {
            launch {
                scale.animateTo(
                    targetValue = 1.0f,
                    animationSpec = tween(
                        durationMillis = 1000,
//                        easing = {
//                            OvershootInterpolator(1f).getInterpolation((it))
//                        }
                    )
                )
            }
            launch {
                offsetX.animateTo(screenWidth.value,  animationSpec = tween(durationMillis = 1000))
            }
            launch {
                offsetY.animateTo(screenHeight.value, animationSpec = tween(durationMillis = 1000))
            }
        }

        job2.join()

        delay(0L)

        nc?.navigate(NavRoutes.Home.route)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        val x = offsetX.value
        val y = offsetY.value
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .scale(scale.value)
                .rotate(angle.value)
                .offset(x.dp, -y.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSrcSplash() {
    ScrSplash(null)
}