package com.example.cookbook.presentation.composables

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.cookbook.R
import com.example.cookbook.presentation.NavRoutes
import kotlinx.coroutines.delay

@Composable
fun Splash(nc: NavController?) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 2.0f,
            animationSpec = tween (
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation((it))
                }
            )
        )
        delay(0L)
        nc?.navigate(NavRoutes.Home.route)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value)
        )
    }
}