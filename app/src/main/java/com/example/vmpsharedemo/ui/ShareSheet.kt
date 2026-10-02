package com.example.vmpsharedemo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vmpsharedemo.ui.theme.VmpBorder
import com.example.vmpsharedemo.ui.theme.VmpCrimson
import com.example.vmpsharedemo.data.BloodRequest
import com.example.vmpsharedemo.share.ShareHelper

// One app icon in the sheet. No real logos: a colored circle with a letter is enough for the demo.
private data class ShareTarget(
    val label: String,
    val letter: String,
    val color: Color,
    val packageName: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareSheet(request: BloodRequest, onDismiss: () -> Unit) {
    val context = LocalContext.current

    // Only apps that are installed are shown (needs the <queries> block in the manifest).
    val targets = listOfNotNull(
        ShareHelper.firstInstalled(context, "com.whatsapp", "com.whatsapp.w4b")
            ?.let { ShareTarget("WhatsApp", "W", Color(0xFF25D366), it) },
        ShareHelper.firstInstalled(context, "com.facebook.orca")
            ?.let { ShareTarget("Messenger", "M", Color(0xFF0084FF), it) },
        ShareHelper.firstInstalled(context, "org.telegram.messenger")
            ?.let { ShareTarget("Telegram", "T", Color(0xFF2AABEE), it) },
        ShareHelper.firstInstalled(context, "com.facebook.katana")
            ?.let { ShareTarget("Facebook", "f", Color(0xFF1877F2), it) }
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 22.dp)
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = "Share",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Preview card: what the shared request looks like
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .border(1.dp, VmpBorder, RoundedCornerShape(14.dp))
                    .padding(14.dp)
            ) {
                Text(
                    text = "${request.bloodGroup} blood needed",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = VmpCrimson
                )
                Text(
                    text = "${request.hospital}, ${request.location}",
                    fontSize = 14.sp,
                    color = Color(0xFF555555)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = ShareHelper.buildLink(request),
                    fontSize = 12.sp,
                    color = Color(0xFF888888)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // App icons + "More"
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                targets.forEach { target ->
                    AppIcon(target.label, target.letter, target.color) {
                        ShareHelper.shareToPackage(context, request, target.packageName)
                        onDismiss()
                    }
                }
                AppIcon("More", "...", Color(0xFF9E9E9E)) {
                    ShareHelper.shareViaChooser(context, request)
                    onDismiss()
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            ActionRow("Copy link") {
                ShareHelper.copyLink(context, request)
                onDismiss()
            }
            ActionRow("Share via") {
                ShareHelper.shareViaChooser(context, request)
                onDismiss()
            }
        }
    }
}

@Composable
private fun AppIcon(label: String, letter: String, color: Color, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(color)
        ) {
            Text(
                text = letter,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = label, fontSize = 12.sp, color = Color(0xFF333333))
    }
}

@Composable
private fun ActionRow(label: String, onClick: () -> Unit) {
    Text(
        text = label,
        fontSize = 17.sp,
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 14.dp)
    )
}
