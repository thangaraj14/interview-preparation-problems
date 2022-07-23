package lld.auctionSystem.services;

import lld.auctionSystem.models.Event;

import java.util.List;

public interface ObserverService {

    void updateObserver(List<String> ids, Event event);

}
