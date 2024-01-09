package com.chat.service.controller.dto;


import lombok.*;

import java.util.Set;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ConversationDTO {

    private Long conversationId;
    private Set<Long> users;


}
