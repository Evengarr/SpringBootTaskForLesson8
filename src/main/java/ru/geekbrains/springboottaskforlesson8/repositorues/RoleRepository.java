package ru.geekbrains.springboottaskforlesson8.repositorues;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.springboottaskforlesson8.models.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
