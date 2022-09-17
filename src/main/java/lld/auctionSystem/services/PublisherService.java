package lld.auctionSystem.services;


import lld.auctionSystem.enums.ObserverType;
import lld.auctionSystem.models.Event;

import java.util.List;

public interface PublisherService {

    void updateObservers(List<String> observerIds, ObserverType type, Event event);

    boolean subscribe(String subjectId, String observerId, ObserverType type);

    boolean unsubscribe(String subjectId, String observerId, ObserverType type);
}
