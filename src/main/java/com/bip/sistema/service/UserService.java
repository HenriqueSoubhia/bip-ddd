package com.bip.sistema.service;

import com.bip.sistema.dao.UserDAO;
import com.bip.sistema.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDAO userDAO;

    // Injeção via construtor
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // Listar todos os usuários
    public List<User> findAll() {
        return userDAO.findAll();
    }

    // Buscar usuário por id
    public User findById(Long id) {
        return userDAO.findByIdOrBadge(id);
    }

    // Inserir novo usuário
    public void insert(User user) {
        userDAO.insert(user);
    }

    // Atualizar usuário existente
    public void update(User user) {
        userDAO.update(user);
    }

    // Deletar usuário pelo id
    public void delete(Long id) {
        userDAO.delete(id);
    }
}
