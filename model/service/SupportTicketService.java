package model.service;

import model.domain.SupportTicket;
import model.domain.User;
import dto.SupportTicketDTO;
import model.repository.SupportTicketRepository;

import java.util.Optional;

public class SupportTicketService {
    
    private final SupportTicketRepository supportTicketRepository;

    public SupportTicketService(SupportTicketRepository supportTicketRepository) {
        this.supportTicketRepository = supportTicketRepository;
    }

    public void openTicket(String title, String description, User author) {
        SupportTicket newTicket = new SupportTicket(author, description, title);
        this.supportTicketRepository.add(newTicket);
    }

    public Optional<SupportTicketDTO> attendNextTicket() {
        return supportTicketRepository.processNext()
                .map(this::toDto); 
    }

    private SupportTicketDTO toDto(SupportTicket ticket) {
        SupportTicketDTO dto = new SupportTicketDTO();

        dto.setTitle(ticket.getTitle());
        dto.setDescription(ticket.getDescription()); 
        dto.setAuthorEmail(ticket.getUser().getEmail()); 

        return dto;
    }
}