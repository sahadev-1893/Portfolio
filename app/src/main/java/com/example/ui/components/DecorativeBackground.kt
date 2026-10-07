package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke

/**
 * Recreates the subtle thin curved lines, circular outlines, and geometric developer aesthetic
 * from the reference inspiration. Positioned behind all screen content.
 */
@Composable
fun PortfolioDecorativeBackground(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = true
) {
    val infiniteTransition = rememberInfiniteTransition(label = "background_lines_motion")
    
    // Very gentle subtle rotation/oscillation
    val animatedAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 120_000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    val strokeColor = if (isDarkTheme) Color(0x1FFFFFFF) else Color(0x14000000)
    val accentStrokeColor = if (isDarkTheme) Color(0x28FFFFFF) else Color(0x20000000)
    val dotColor = if (isDarkTheme) Color(0x35FFFFFF) else Color(0x2A000000)

    Box(modifier = modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // 1. Soft radial background glow at top-right
            val radialCenter = Offset(w * 0.85f, h * 0.15f)
            drawCircle(
                brush = Brush.radialGradient(
                    colors = if (isDarkTheme) listOf(
                        Color(0x18FFFFFF),
                        Color(0x05FFFFFF),
                        Color.Transparent
                    ) else listOf(
                        Color(0x0C000000),
                        Color.Transparent
                    ),
                    center = radialCenter,
                    radius = w * 0.9f
                ),
                radius = w * 0.9f,
                center = radialCenter
            )

            // 2. Large concentric decorative circles at top right (signature reference element)
            val ringCenter = Offset(w * 0.92f, h * 0.18f)
            val baseRadius = w * 0.45f

            drawCircle(
                color = strokeColor,
                radius = baseRadius,
                center = ringCenter,
                style = Stroke(width = 1.2f)
            )
            drawCircle(
                color = strokeColor,
                radius = baseRadius * 1.55f,
                center = ringCenter,
                style = Stroke(width = 1.0f)
            )
            drawCircle(
                color = accentStrokeColor,
                radius = baseRadius * 2.15f,
                center = ringCenter,
                style = Stroke(width = 0.8f)
            )

            // 3. Curved arc path sweeping across mid-screen
            val arcPath = Path().apply {
                moveTo(-w * 0.2f, h * 0.45f)
                cubicTo(
                    w * 0.35f, h * 0.35f,
                    w * 0.65f, h * 0.60f,
                    w * 1.2f, h * 0.50f
                )
            }
            drawPath(
                path = arcPath,
                color = strokeColor,
                style = Stroke(width = 1.0f, cap = StrokeCap.Round)
            )

            // 4. Secondary lower curved ring
            val lowerCenter = Offset(w * 0.15f, h * 0.88f)
            drawCircle(
                color = strokeColor,
                radius = w * 0.55f,
                center = lowerCenter,
                style = Stroke(width = 1.0f)
            )
            drawCircle(
                color = accentStrokeColor,
                radius = w * 0.85f,
                center = lowerCenter,
                style = Stroke(width = 0.75f)
            )

            // 5. Minimalist crosshair markings for technical precision aesthetic
            val crosshairs = listOf(
                Offset(w * 0.12f, h * 0.10f),
                Offset(w * 0.88f, h * 0.42f),
                Offset(w * 0.15f, h * 0.72f)
            )
            val markLen = 6f
            crosshairs.forEach { pt ->
                drawLine(
                    color = dotColor,
                    start = Offset(pt.x - markLen, pt.y),
                    end = Offset(pt.x + markLen, pt.y),
                    strokeWidth = 1.2f
                )
                drawLine(
                    color = dotColor,
                    start = Offset(pt.x, pt.y - markLen),
                    end = Offset(pt.x, pt.y + markLen),
                    strokeWidth = 1.2f
                )
            }
        }
    }
}
