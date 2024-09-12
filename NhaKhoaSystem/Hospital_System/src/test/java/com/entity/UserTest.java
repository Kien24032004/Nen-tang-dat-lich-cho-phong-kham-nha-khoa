package com.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testUserGettersAndSetters() {
        // Arrange
        User user = new User();
        user.setId(1);
        user.setFullName("Alice Johnson");
        user.setEmail("alice.johnson@example.com");
        user.setPassword("securepassword");

        // Act & Assert
        assertEquals(1, user.getId());
        assertEquals("Alice Johnson", user.getFullName());
        assertEquals("alice.johnson@example.com", user.getEmail());
        assertEquals("securepassword", user.getPassword());
    }
}
