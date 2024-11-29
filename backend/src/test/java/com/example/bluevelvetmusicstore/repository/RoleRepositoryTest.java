package com.example.bluevelvetmusicstore.repository;

import com.example.bluevelvetmusicstore.model.entities.Role;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testRolesExistemComDescricoesCorretas() {
        // Verifica o role "Administrator"
        Optional<Role> adminRoleOpt = roleRepository.findByName("Administrator");
        assertTrue(adminRoleOpt.isPresent(), "A role 'Administrator' deveria existir");
        assertEquals("Manages everything", adminRoleOpt.get().getDescription());

        // Verifica o role "Sales Manager"
        Optional<Role> salesManagerRoleOpt = roleRepository.findByName("Sales Manager");
        assertTrue(salesManagerRoleOpt.isPresent(), "A role 'Sales Manager' deveria existir");
        assertEquals("Manages product price, customers, shipping, orders and sales report", salesManagerRoleOpt.get().getDescription());

        // Verifica o role "Editor"
        Optional<Role> editorRoleOpt = roleRepository.findByName("Editor");
        assertTrue(editorRoleOpt.isPresent(), "A role 'Editor' deveria existir");
        assertEquals("Manages categories, brands, products, articles, and menus", editorRoleOpt.get().getDescription());

        // Verifica o role "Shipping Manager"
        Optional<Role> shippingManagerRoleOpt = roleRepository.findByName("Shipping Manager");
        assertTrue(shippingManagerRoleOpt.isPresent(), "A role 'Shipping Manager' deveria existir");
        assertEquals("Views products, view orders, and update order status", shippingManagerRoleOpt.get().getDescription());

        // Verifica o role "Assistant"
        Optional<Role> assistantRoleOpt = roleRepository.findByName("Assistant");
        assertTrue(assistantRoleOpt.isPresent(), "A role 'Assistant' deveria existir");
        assertEquals("Manages questions and reviews", assistantRoleOpt.get().getDescription());
    }
}
