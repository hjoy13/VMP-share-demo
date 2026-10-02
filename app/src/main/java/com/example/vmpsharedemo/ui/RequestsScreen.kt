package com.example.vmpsharedemo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RequestsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(horizontal = 22.dp)
    ) {
        Text(
            text = "←",
            fontSize = 32.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "♥",
                fontSize = 28.sp,
                color = Color(0xFFB51E4A)
            )

            Text(
                text = "  Blood donation",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Your blood type is never shown to anyone unless you choose to respond to a specific request. There is no donor directory.",
            fontSize = 16.sp,
            lineHeight = 22.sp,
            color = Color(0xFF666666),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Requests",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF6C4CF5))
                    .padding(
                        horizontal = 24.dp,
                        vertical = 10.dp
                    )
            )

            Text(
                text = "My requests",
                fontSize = 18.sp,
                color = Color(0xFF555555),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 28.dp)
            )

            Text(
                text = "Donor",
                fontSize = 18.sp,
                color = Color(0xFF555555)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(Color(0xFFEAF6EF))
                .padding(
                    horizontal = 16.dp,
                    vertical = 14.dp
                )
        ) {
            Text(
                text = "✓  You can donate now",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1E1E1E)
            )
        }
    }
}