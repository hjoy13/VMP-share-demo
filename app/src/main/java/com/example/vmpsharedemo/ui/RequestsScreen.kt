package com.example.vmpsharedemo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vmpsharedemo.data.BloodRequest
import com.example.vmpsharedemo.data.Urgency
import com.example.vmpsharedemo.data.sampleBloodRequests

@Composable
fun RequestsScreen() {
    var sharingRequest by remember { mutableStateOf<BloodRequest?>(null) }

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

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(sampleBloodRequests) { request ->
                RequestCard(request, onShare = { sharingRequest = request })
            }
        }
    }

    // Share sheet is shown while a request is selected for sharing
    sharingRequest?.let { request ->
        ShareSheet(request = request, onDismiss = { sharingRequest = null })
    }
}

@Composable
private fun UrgencyChip(urgency: Urgency) {
    // label, text color, background color for each urgency level
    val (label, textColor, bgColor) = when (urgency) {
        Urgency.URGENT -> Triple("Urgent", Color(0xFFB51E4A), Color(0xFFFCE4EC))
        Urgency.NEEDED_TODAY -> Triple("Needed today", Color(0xFFB45F06), Color(0xFFFFF0DC))
        Urgency.NORMAL -> Triple("Normal", Color(0xFF2E7D32), Color(0xFFEAF6EF))
    }
    Text(
        text = label,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        color = textColor,
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(bgColor)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    )
}

@Composable
private fun RequestCard(request: BloodRequest, onShare: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(14.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = request.bloodGroup,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFB51E4A)
            )
            Text(
                text = "  ${request.patientName}",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )
            UrgencyChip(request.urgency)
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "${request.hospital}, ${request.location}",
            fontSize = 15.sp,
            color = Color(0xFF555555)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "${request.unitsNeeded} unit(s) needed · ${request.postedTime}",
            fontSize = 14.sp,
            color = Color(0xFF777777)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { /* demo: does nothing */ },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6C4CF5)),
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Offer to donate", fontSize = 15.sp)
            }

            // Share button: Step 6 opens the share sheet
            OutlinedButton(
                onClick = onShare,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Share",
                    fontSize = 15.sp,
                    color = Color(0xFF6C4CF5)
                )
            }
        }
    }
}