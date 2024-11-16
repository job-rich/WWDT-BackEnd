package com.wwdt.workspace.infra

import com.wwdt.workspace.domain.Role
import com.wwdt.workspace.domain.enums.Authority
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class RoleRepositoryTest {
    @Autowired
    lateinit var roleRepository: RoleRepository

    @Test
    fun `권한으로 역할 조회`() {
        // given
        val roles = listOf(
            Role(authority = Authority.OWNER, description = "Manage all permissions"),
            Role(authority = Authority.MANAGE, description = "Manage all permissions"),
            Role(authority = Authority.WRITE, description = "Write permissions"),
            Role(authority = Authority.READ, description = "Read permissions")
        )
        roleRepository.saveAll(roles)

        // when
        val foundRole = roleRepository.findByAuthority(Authority.OWNER)

        // then
        assertThat(foundRole).isNotNull
        assertThat(foundRole.authority).isEqualTo(Authority.OWNER)

        // when
        val foundRole2 = roleRepository.findByAuthority(Authority.MANAGE)

        // then
        assertThat(foundRole2).isNotNull
        assertThat(foundRole2.authority).isEqualTo(Authority.MANAGE)

        // when
        val foundRole3 = roleRepository.findByAuthority(Authority.WRITE)

        // then
        assertThat(foundRole3).isNotNull
        assertThat(foundRole3.authority).isEqualTo(Authority.WRITE)

        // when
        val foundRole4 = roleRepository.findByAuthority(Authority.READ)

        // then
        assertThat(foundRole4).isNotNull
        assertThat(foundRole4.authority).isEqualTo(Authority.READ)
    }
}