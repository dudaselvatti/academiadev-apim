package model.repository.impl;

import model.domain.SupportTicket;
import model.repository.DataStore;
import model.repository.SupportTicketRepository;
import java.util.Optional;

public class InMemorySupportTicketRepositoryImpl implements SupportTicketRepository {

    @Override
    public void add(SupportTicket ticket) {
        DataStore.SUPPORT_TICKETS.add(ticket);
    }

    @Override
    public Optional<SupportTicket> processNext() {

        SupportTicket nextTicket = DataStore.SUPPORT_TICKETS.poll();
        return Optional.ofNullable(nextTicket);
        
    }
}