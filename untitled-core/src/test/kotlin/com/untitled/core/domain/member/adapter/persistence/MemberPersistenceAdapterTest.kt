package com.untitled.core.domain.member.adapter.persistence

import com.untitled.core.domain.member.domain.MemberFixture
import com.untitled.core.domain.member.domain.MemberNotExistsException
import com.untitled.core.lib.IntegrationTest
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.date.shouldBeAfter
import io.kotest.matchers.date.shouldBeBefore
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

@IntegrationTest
class MemberPersistenceAdapterTest(
    private val memberPersistenceAdapter: MemberPersistenceAdapter,
    private val memberRepository: MemberRepository,
) : StringSpec({

    afterEach {
        memberRepository.deleteAll()
    }

    "멤버를 저장합니다" {
        // given
        val member = MemberFixture.create()

        // when
        memberPersistenceAdapter.save(member = member)

        // then
        val members = memberRepository.findAll()
        members shouldHaveSize 1
        members[0].also {
            it.id shouldBeGreaterThan 0L
            it.name shouldBe member.name
            it.memberType shouldBe member.memberType
            it.social.socialId shouldBe member.socialId
            it.social.socialType shouldBe member.socialType

            it.createdAt shouldBeAfter LocalDateTime.now().minusMinutes(1)
            it.createdAt shouldBeBefore LocalDateTime.now().plusMinutes(1)

            it.updatedAt shouldBeAfter LocalDateTime.now().minusMinutes(1)
            it.updatedAt shouldBeBefore LocalDateTime.now().plusMinutes(1)
        }
    }

    "기존에 존재하는 멤버를 수정 합니다" {
        // given
        val member = MemberFixture.create()
        val entity = memberRepository.save(member.toEntity())

        // when
        memberPersistenceAdapter.patch(memberId = entity.id, name = "바보에요")

        // then
        val members = memberRepository.findAll()
        members shouldHaveSize 1
        members[0].also {
            it.id shouldBeGreaterThan 0L
            it.name shouldBe "바보에요"
            it.memberType shouldBe member.memberType
            it.social.socialId shouldBe member.socialId
            it.social.socialType shouldBe member.socialType

            it.createdAt shouldBeAfter LocalDateTime.now().minusMinutes(1)
            it.createdAt shouldBeBefore LocalDateTime.now().plusMinutes(1)

            it.updatedAt shouldBeAfter LocalDateTime.now().minusMinutes(1)
            it.updatedAt shouldBeBefore LocalDateTime.now().plusMinutes(1)
        }
    }

    "존재하지 않는 멤버를 수정할 수 없다" {
        // when
        shouldThrowExactly<MemberNotExistsException> {
            memberPersistenceAdapter.patch(memberId = -1L, name = "바보에요")
        }

        // then
        val members = memberRepository.findAll()
        members shouldHaveSize 0
    }

})
