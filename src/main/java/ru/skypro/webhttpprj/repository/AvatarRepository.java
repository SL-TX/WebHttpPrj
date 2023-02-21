package ru.skypro.webhttpprj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.webhttpprj.model.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}