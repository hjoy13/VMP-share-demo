package com.example.vmpsharedemo.data
enum class Urgency {
    URGENT,
    NEEDED_TODAY,
    NORMAL
}

data class BloodRequest(
    val id: String,
    val patientName: String,
    val bloodGroup: String,
    val hospital: String,
    val location: String,
    val unitsNeeded: Int,
    val urgency: Urgency,
    val postedTime: String
)

val sampleBloodRequests = listOf(
    BloodRequest(
        id = "blood_001",
        patientName = "Rahim Ahmed",
        bloodGroup = "O-",
        hospital = "Central Hospital",
        location = "Sylhet",
        unitsNeeded = 2,
        urgency = Urgency.URGENT,
        postedTime = "10 min ago"
    ),
    BloodRequest(
        id = "blood_002",
        patientName = "Nusrat Jahan",
        bloodGroup = "A+",
        hospital = "City Medical Centre",
        location = "Sylhet",
        unitsNeeded = 1,
        urgency = Urgency.NEEDED_TODAY,
        postedTime = "35 min ago"
    ),
    BloodRequest(
        id = "blood_003",
        patientName = "Farhan Hasan",
        bloodGroup = "B+",
        hospital = "North Point Hospital",
        location = "Sylhet",
        unitsNeeded = 3,
        urgency = Urgency.NORMAL,
        postedTime = "1 hr ago"
    )
)

// The request pre-filled in the "Ask for blood" form (Step 7). Fictional data.
val newBloodRequest = BloodRequest(
    id = "blood_004",
    patientName = "Sadia Rahman",
    bloodGroup = "A-",
    hospital = "Riverside Hospital",
    location = "Sylhet",
    unitsNeeded = 2,
    urgency = Urgency.URGENT,
    postedTime = "Just now"
)
