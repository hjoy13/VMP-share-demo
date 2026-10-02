package com.example.vmpsharedemo.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vmpsharedemo.ui.theme.VmpBorder
import com.example.vmpsharedemo.ui.theme.VmpPurple
import com.example.vmpsharedemo.data.newBloodRequest

// "Ask for blood" form. Demo only: every field is pre-filled and read-only, no validation.
@Composable
fun CreateRequestScreen(onBack: () -> Unit, onDone: () -> Unit) {
    val request = newBloodRequest
    var showPopup by remember { mutableStateOf(false) }
    var showShare by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(horizontal = 22.dp)
    ) {
        Text(
            text = "←",
            fontSize = 32.sp,
            color = Color.Black,
            modifier = Modifier.clickable { onBack() }
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Ask for blood",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        FormField("Blood group needed", request.bloodGroup)
        FormField("Patient name", request.patientName)
        FormField("Hospital", request.hospital)
        FormField("Location", request.location)
        FormField("Units needed", request.unitsNeeded.toString())
        FormField("Urgency", "Urgent")

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { showPopup = true },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = VmpPurple),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Post request", fontSize = 16.sp)
        }
    }

    // After posting: popup first. "Share to reach more donors" closes it and opens the share sheet.
    if (showPopup) {
        PostedPopup(
            onShare = {
                showPopup = false
                showShare = true
            },
            onDismiss = {
                showPopup = false
                onDone()
            }
        )
    }

    if (showShare) {
        ShareSheet(
            request = request,
            onDismiss = {
                showShare = false
                onDone()
            }
        )
    }
}

@Composable
private fun FormField(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, VmpBorder, RoundedCornerShape(10.dp))
            .padding(horizontal = 14.dp, vertical = 10.dp)
    ) {
        Text(text = label, fontSize = 12.sp, color = Color(0xFF777777))
        Text(text = value, fontSize = 17.sp, color = Color.Black)
    }
}
