package com.example.vmpsharedemo.share

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import com.example.vmpsharedemo.data.BloodRequest
import com.example.vmpsharedemo.data.Urgency

object ShareHelper {

    // PROTOTYPE URL: /blood/<id> is a proposed route, not a real VMP page.
    // Verified VMP routes are only /post/<id> and /page/<id>.
    private const val BLOOD_LINK_BASE = "https://web.vmpmessenger.com/blood/"

    fun buildLink(request: BloodRequest): String = BLOOD_LINK_BASE + request.id

    fun buildMessage(request: BloodRequest): String {
        val urgencyText = when (request.urgency) {
            Urgency.URGENT -> "Urgent"
            Urgency.NEEDED_TODAY -> "Needed today"
            Urgency.NORMAL -> "Needed"
        }
        return "$urgencyText: ${request.bloodGroup} blood needed " +
            "(${request.unitsNeeded} unit(s)) at ${request.hospital}, ${request.location}. " +
            "Can you help or share this with someone who can?\n" +
            buildLink(request)
    }

    // Returns the first of these packages that is installed, or null if none is.
    // Needs the <queries> block in AndroidManifest.xml (Android 11+).
    fun firstInstalled(context: Context, vararg packages: String): String? =
        packages.firstOrNull { pkg ->
            try {
                context.packageManager.getPackageInfo(pkg, 0)
                true
            } catch (e: PackageManager.NameNotFoundException) {
                false
            }
        }

    // Sends the message straight to one app. Falls back to the chooser if it fails.
    fun shareToPackage(context: Context, request: BloodRequest, pkg: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, buildMessage(request))
            setPackage(pkg)
        }
        try {
            context.startActivity(intent)
            Log.d(TAG, "share_app_launched package=$pkg request=${request.id}")
        } catch (e: ActivityNotFoundException) {
            shareViaChooser(context, request)
        }
    }

    // "Share via": the normal Android chooser with every app that can take text.
    fun shareViaChooser(context: Context, request: BloodRequest) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, buildMessage(request))
        }
        context.startActivity(Intent.createChooser(intent, "Share via"))
        Log.d(TAG, "share_chooser_opened request=${request.id}")
    }

    fun copyLink(context: Context, request: BloodRequest) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText("VMP link", buildLink(request)))
        Toast.makeText(context, "Link copied", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "share_link_copied request=${request.id}")
    }

    private const val TAG = "VMP_SHARE"
}
