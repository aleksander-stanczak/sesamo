package pl.waw.sezamkowo.core.application.child.command

import pl.waw.sezamkowo.core.application.child.dto.ChildDto

class UpdateChildCommand(val childId: String, val childDto: ChildDto)