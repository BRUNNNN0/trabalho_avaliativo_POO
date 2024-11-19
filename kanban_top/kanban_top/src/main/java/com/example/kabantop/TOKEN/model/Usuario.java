package com.example.kabantop.TOKEN.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
public class Usuario {



    @Entity
    @Table(name = "users")
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(of = "id")
    public class Usario implements UserDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private String id;

        private String login;

        private String password;

        @Enumerated(EnumType.STRING)
        private UsuarioRole rotk;

        public Usario(String login, String password, UsuarioRole rotk) {
            this.login = login;
            this.password = password;
            this.rotk = rotk;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            if (this.rotk == UsuarioRole.ADMIN) {
                return List.of(
                        new SimpleGrantedAuthority("ROLE_ADMIN"),
                        new SimpleGrantedAuthority("ROLE_USER")
                );
            }
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getLogin() {
            return login;
        }


        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}