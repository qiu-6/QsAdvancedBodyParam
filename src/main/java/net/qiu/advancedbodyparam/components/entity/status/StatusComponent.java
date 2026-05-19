package net.qiu.advancedbodyparam.components.entity.status;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.qiu.advancedbodyparam.util.BodyParts;
import net.qiu.advancedbodyparam.util.status.Status;
import net.qiu.advancedbodyparam.util.status.StatusHelper;

import java.util.HashSet;

public interface StatusComponent extends Component, ServerTickingComponent, AutoSyncedComponent {

    HashSet<StatusHelper> getStatusSet(BodyParts part);
    void addStatus(BodyParts part, Status status, int duration, int intensity);
}
