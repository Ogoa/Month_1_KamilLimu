package com.example.month1.ui.data

import com.example.month1.R

object DataSource {

    /**
     * Pillars and type of sessions held
     */
    val theorySessions = listOf(
        PillarItem(
            pillarName = R.string.community_engagement_pillar,
            pillarDescription = R.string.community_engagement_about
        ),
        PillarItem(
            pillarName = R.string.personal_development_pillar,
            pillarDescription = R.string.personal_development_about
        ),
        PillarItem(
            pillarName = R.string.innovation_pillar,
            pillarDescription = R.string.innovation_about
        )
    )

    val tellAndShowSessions = listOf(
        PillarItem(
            pillarName = R.string.professional_development_pillar,
            pillarDescription = R.string.professional_development_about
        ),
        PillarItem(
            pillarName = R.string.innovation_pillar,
            pillarDescription = R.string.innovation_about
        )
    )

    val complementarySessions = listOf(
        PillarItem(
            pillarName = R.string.community_engagement_pillar,
            pillarDescription = R.string.community_engagement_about
        )
    )

    /**
     * Events categorised according to their session type and pillar
     */
    val theoryEvents = listOf(
        // Launch
        EventSessionItem(
            eventTitle = R.string.launch,
            eventTakeaways = R.string.launch_takeaways,
            eventApplicationNotes = R.string.launch_apply,
            category = "Community Engagement"
        ),
        // Intro to innovation
        EventSessionItem(
            eventTitle = R.string.intro_to_innovation,
            eventTakeaways = R.string.intro_to_innovation_takeaways,
            eventApplicationNotes = R.string.intro_to_innovation_apply,
            category = "Innovation"
        ),
        // How to learn
        EventSessionItem(
            eventTitle = R.string.how_to_learn,
            eventTakeaways = R.string.how_to_learn_takeaways,
            eventApplicationNotes = R.string.how_to_learn_apply,
            category = "Personal Development"
        )
    )

    val tellAndShowEvents = listOf(
        // Resume writing
        EventSessionItem(
            eventTitle = R.string.resume_writing,
            eventTakeaways = R.string.resume_writing_takeaways,
            eventApplicationNotes = R.string.resume_writing_apply,
            category = "Professional Development"
        ),
        // ICT Cybersecurity
        EventSessionItem(
            eventTitle = R.string.ict_cybersecurity,
            eventTakeaways = R.string.ict_cyber_takeaways,
            eventApplicationNotes = R.string.ict_cyber_apply,
            category = "Professional Development"
        ),

        /*TODO: Add 'Innovations In Real Life'*/
    )

    val complementaryEvents = listOf(
        // Meet an alumni
        EventSessionItem(
            eventTitle = R.string.meet_an_alumni,
            eventTakeaways = R.string.meet_an_alumni_takeaways,
            eventApplicationNotes = R.string.meet_an_alumni_apply,
            category = "Community Engagement"
        )
    )
}