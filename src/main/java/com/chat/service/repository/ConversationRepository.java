package com.chat.service.repository;

import com.chat.service.repository.data.ConversationEntity;
import com.chat.service.repository.data.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConversationRepository extends CrudRepository<ConversationEntity, Long> {

}
