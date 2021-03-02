package com.jetbrains.handson.httpapi.factories

import com.jetbrains.handson.httpapi.models.Literature

class LiteratureFactory: SlaveFactory<Literature> {
    override val content: MutableSet<Literature> = hashSetOf(
        Literature("Satan`s Bible", "Anton Szandor LaVey", 1978),
        Literature("Peace of Earth", "Stanislav Lem", 1987),
        Literature("Kill every juda", "Jesus Christ", 23)
    )
}