package com.pashuit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pashuit.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
