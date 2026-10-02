package com.example.vmpsharedemo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vmpsharedemo.ui.theme.VmpPurple

// Bottom popup after posting, like VMP's real one, plus the new share action.
// The donor groups are static text copied from VMP's popup: no compatibility logic.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostedPopup(onShare: () -> Unit, onDismiss: () -> Unit) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.White
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 22.dp)
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = "Request posted",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "It will reach donors with: O-, A-",
                fontSize = 16.sp,
                color = Color(0xFF555555)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onShare,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = VmpPurple),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Share to reach more donors", fontSize = 16.sp)
            }

            Text(
                text = "Dismiss",
                fontSize = 16.sp,
                color = Color(0xFF555555),
                modifier = Modifier
                    .clickable { onDismiss() }
                    .padding(vertical = 14.dp)
            )
        }
    }
}
