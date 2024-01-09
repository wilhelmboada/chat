package com.chat.service.repository;

import com.chat.service.repository.data.MessageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, Long> {

}
