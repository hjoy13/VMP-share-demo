# VMP Share Demo

A small Android prototype that explores external sharing for blood requests: a share action on each request, and a share prompt right after posting one.

It is a demo with fictional data. It has no backend and is not the real VMP Messenger app.

## What it shows

1. **Blood requests list:** request cards with an urgency chip, an "Offer to donate" button and a **Share** button.
2. **Share sheet:** a bottom sheet with a preview card, the share apps installed on the phone (WhatsApp, Messenger, Telegram, Facebook), **Copy link** and **Share via** (system chooser). Missing apps are hidden.
3. **Ask for blood:** *My requests* tab, then *Ask for blood*, then *Post request*. A popup confirms the post and offers **Share to reach more donors**, which opens the same share sheet.

## Notes

- All names, hospitals and requests are made up.
- The shared link (`https://web.vmpmessenger.com/blood/<id>`) is a **prototype URL**. That route does not exist, so opening it does not show the request.
- The "It will reach donors with: O-, A-" text in the popup is static. There is no donor matching.
- Messenger and Facebook appear in the share sheet but are not tested. Facebook ignores pre-filled text and keeps only the link.
- Android cannot confirm that a share was actually sent inside the target app. The demo only logs that an app was launched (Logcat tag `VMP_SHARE`).

## Tech

Kotlin, Jetpack Compose (Material 3), minimum SDK 24, package `com.example.vmpsharedemo`.

## Build and install

Requires Android Studio (or a JDK and the Android SDK) and a phone with USB debugging enabled.

```bash
./gradlew assembleDebug
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

Or open the project in Android Studio and press Run.

To see the share events:

```bash
adb logcat -s VMP_SHARE
```

## Project layout

```
app/src/main/java/com/example/vmpsharedemo/
├── MainActivity.kt            # entry point, switches between the two screens
├── data/BloodRequest.kt       # data class, urgency enum, fictional sample requests
├── share/ShareHelper.kt       # message and link builder, share intents, copy link
└── ui/
    ├── RequestsScreen.kt      # list, tabs, request cards
    ├── ShareSheet.kt          # share bottom sheet
    ├── CreateRequestScreen.kt # ask for blood form
    ├── PostedPopup.kt         # post-publish popup
    └── theme/                 # colors and theme
```
