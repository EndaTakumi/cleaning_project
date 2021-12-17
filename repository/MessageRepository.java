package jp.co.sss.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.project.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{

}
