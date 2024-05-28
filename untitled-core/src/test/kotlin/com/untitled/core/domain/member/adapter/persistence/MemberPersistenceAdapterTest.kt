package com.untitled.core.domain.member.adapter.persistence

import com.untitled.core.domain.member.domain.MemberFixture
import com.untitled.core.lib.IntegrationTest
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

@IntegrationTest
class MemberPersistenceAdapterTest(
    private val memberPersistenceAdapter: MemberPersistenceAdapter,
    private val memberRepository: MemberRepository,
) : StringSpec({

    "멤버를 저장합니다" {
        // given
        val member = MemberFixture.create()

        // when
        memberPersistenceAdapter.saveMember(member = member)

        // then
        val members = memberRepository.findAll()
        members shouldHaveSize 1
        members[0].also {
            it.name shouldBe member.name
            it.memberType shouldBe member.memberType
            it.social.socialId shouldBe member.socialId
            it.social.socialType shouldBe member.socialType
        }
    }

})
