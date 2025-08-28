package model.repository;

import model.domain.SupportTicket;
import java.util.Optional;

public interface SupportTicketRepository {
    
    void add(SupportTicket ticket);

    Optional<SupportTicket> processNext();

}
